<?php

	require_once ('Connect.php'); 
			
	//connection to database	
	$db = new Connect();
	$Conn = $db->connect();
	
	$response = array("error" => FALSE); //json array response
	
	//isset function is applicable for variable is set or not
	if(isset($_POST['chaptername']) && isset($_POST['checked']) && isset($_POST['tablename'])) {
		
		//got details
		$chaptername = $_POST['chaptername'];
		$checked = $_POST['checked'];
		$tablename = $_POST['tablename'];
		
		$sql = "SELECT * FROM $tablename WHERE chaptername = '".$chaptername."'";
		$query = mysqli_query($Conn, $sql);
		$result = mysqli_num_rows($query);
		//check chaptername exists or not
		if ($result > 0) {
			$sql = "UPDATE $tablename SET workingdata= 0, checked = '".$checked."' WHERE chaptername = '".$chaptername."'";
			if (mysqli_query($Conn, $sql)) {
				//show data in json format ...easy to pull data in app
				$response["error"] = FALSE;
				$response["result"] = "Unsaved in database";
				echo json_encode($response);
			} else {
				//failed to update
				$response["error"] = TRUE;
				$response["error_msg"] = "Failed to update";
				echo json_encode($response);
			}
		} else {
			//already existed chaptername in db
			$response["error"] = TRUE;
			$response["error_msg"] = "Chapter name not found";
			echo json_encode($response);		
		}
	}else{
		//invalid approach
		$response["error"] = TRUE;
		$response["error_msg"] = "Invalid approach!";
		echo json_encode($response);
	}

?>