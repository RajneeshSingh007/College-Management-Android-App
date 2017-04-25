<?php
 
 	require_once ('DB_Functions.php');
	
	//connection to db and constructor of function class
	$db = new DB_Functions();
	
	// Path to move uploaded files
	$target_path = "uploads/";
	 
	$response = array("error" => FALSE); //json array response
	 
	// getting server ip address
	$server_ip = gethostbyname(gethostname());
	 	
	if (isset($_POST['name']) && isset($_FILES['file']['name'])) {
		
		$target_path = $target_path . basename($_FILES['file']['name']);
	 
		// reading other post parameters
		$name = $_POST['name'];
		$file = $_FILES['file']['name'];
		
		//$response['FileName'] = basename($file);
	 
	 	// final file url that is being uploaded
		$file_upload_url = 'http://' . $server_ip.'/'.'demo'.'/'.$target_path;
		
		//$imageFileType = pathinfo($target_path,PATHINFO_EXTENSION); //file type 
	
		$fname_arr = explode('.',$file); 
		$fileext = $fname_arr[count($fname_arr)-1];
		$ext_arr = array('doc','pdf','jpeg','png','jpg'); 
		try {
			
				if(in_array($fileext,$ext_arr)) {
					// Throws exception incase file is not being moved
						if (!move_uploaded_file($_FILES['file']['tmp_name'], $target_path)) {
							// failed to move file
							$response['error'] = TRUE;
							$response['error_msg'] = 'Could not move the file, Please check file';
						}
						if($db->checkFileCivilExists($name, $file_upload_url)){
							$reponse['error'] = FALSE;
							$response['error_msg'] = 'File already exists';
						}else{
							$storedata = $db->storeCivilUploadData($name, $file_upload_url);
							if($storedata){
								// File successfully uploaded
								$response['success_message'] = 'File uploaded successfully!';
								$response['error'] = FALSE;
								$reponse['database'] = 'Stored successfully';
								$response['file_path'] = $file_upload_url . basename($_FILES['file']['name']);
							}else{
								// failed to save data into db
								$response['error'] = TRUE;
								$response['error_msg'] = 'failed to save data into db';
							}
						}
				} else{
						$response['error'] = TRUE;
						$response['error_msg'] = 'Sorry, only JPG, JPEG, PNG & PDf files are allowed.';
				}
		
			} catch (Exception $e) {
				// Exception occurred. Make error flag true
				$response['error'] = TRUE;
				$response['error_msg'] = $e->getMessage();
			}
	} else {
		// File parameter is missing
		$response['error'] = TRUE;
		$response['error_msg'] = 'invalid approach';
	}
	 
	// json reponse
	echo json_encode($response);
?>