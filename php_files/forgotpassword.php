<?php

	require_once ('DB_Functions.php');

	//connection to db and constructor of function class
	$db = new DB_Functions();

	$response = array("error" => false); // array

	//isset function is applicable for variable is set or not
	if(isset($_POST['username']) && isset($_POST['grno']) && isset($_POST['password']) && isset($_POST['confirmpassword'])) {

		//got details
		$username = $_POST['username'];
		$grno = $_POST['grno'];
		$password = $_POST['password'];
		$confirmpassword = $_POST['confirmpassword'];

		$success = "Password is changed and notification is sent to your email";

		//check user exists or not
		if($db->checkifuserexisted($username)){

			if(!$db->checkifusersgrexisted($grno)){
				// user is not found with student id
				$response["error"] = TRUE;
				$response["error_msg"] = "StudentId not found";
				echo json_encode($response);
			}else{
				if ($password == $confirmpassword) {
					$user = $db->forgotPassword($password, $confirmpassword, $grno, $username); // get username & password
					if($user){
						$notify = $db->sendemailnotify($username, $password);
						$response["error"] = FALSE;
						$response["user"]["username"] = $username;
						$response["user"]["success"] = $success;
						echo json_encode($response); //change into json format
					}else{
						// user is not found with the credentials
						$response["error"] = TRUE;
						$response["error_msg"] = "Failed to reset password";
						echo json_encode($response);
					}
				}else{
					$response["error"] = TRUE;
					$response["error_msg"] = "password match failed";
					echo json_encode($response);
				}
			}
		}else{
			// user is not found with the credential
			$response["error"] = TRUE;
			$response["error_msg"] = "Login credentials are wrong. Please try again!";
			echo json_encode($response);
		}
	}else{
		//required parameters missing
		$response["error"] = TRUE;
		$response["error_msg"] = "Required parameters is missing!";
		echo json_encode($response);
	}

?>