<?php

	require_once ('DB_Functions.php');
	
	//connection to db and constructor of function class
	$db = new DB_Functions();
	
	$response = array("error" => FALSE); //response
	
	//isset function is applicable for variable is set or not
	if(isset($_POST['fullname']) && isset($_POST['username']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['confirmpassword'])) {
		
		//got details
		$fullname = $_POST['fullname'];
		$username = $_POST['username'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$confirmpassword = $_POST['confirmpassword'];
		$isValid = $db->isValidEmail($email);

		//check user exists or not
		if($db->checkifuserexisted($username)){
			
			//already existed username in db
			$response["error"] = TRUE;
			$response["error_msg"] = "User already existed with " . $email;
			echo json_encode($response);
			
		}else{
			if($isValid){
				if ($password == $confirmpassword) {
					$user = $db->storeUsersData($fullname, $username, $email, $password, $confirmpassword); //create new user
				   if($user != false){
					   	$verify = $db->sendemailverify($email, $username, $password); //send email for verification
						//show response in json format (JAVASCRIPT OBJECT NOTATION)
						$response["error"] = FALSE;
						$response["uid"] = $user["unique_id"];
						$response["user"]["fullname"] = $user["fullname"];
						$response["user"]["username"] = $user["username"];
						$response["user"]["email"] 	= $user["email"];
						$response["user"]["created_at"] = $user["created_at"];
						$response["user"]["updated_at"] = $user["updated_at"];
						echo json_encode($response);
					}else{
						// failed to register account
						$response["error"] = TRUE;
						$response["error_msg"] = "failed to register account";
						echo json_encode($response);
					}
				}else {
				   // password match failed
					$response["error"] = TRUE;
					$response["error_msg"] = "password match failed";
					echo json_encode($response);
				}
				
			}else {
				   // email match failed
					$response["error"] = TRUE;
					$response["error_msg"] = "enter valid email";
					echo json_encode($response);
			}
			
		}
	}else{
		//required parameters missing
		$response["error"] = TRUE;
		$response["error_msg"] = "Required parameters is missing!";
		echo json_encode($response);
	}

?>