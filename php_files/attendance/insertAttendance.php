<?php

	require_once ('Connect.php');
	
	//connection to db
	$db = new Connect();
	$Conn = $db->connect();
	
	$response = array("error" => FALSE); //response
	
	
	//isset function is applicable for variable is set or not
	if(isset($_POST['tablename']) && isset($_POST['atrollno']) && isset($_POST['atdate']) && isset($_POST['atstatus'])) {
		
		//got details
		$rollnos = $_POST['atrollno'];
		$date = $_POST['atdate'];
		$atstatus = $_POST['atstatus'];
		$tablename = $_POST['tablename'];
		
		$sql ="INSERT INTO $tablename(atdate, atstatus, atrollno) VALUES ('".$date."', '".$atstatus."', '".$rollnos."')";
		//$storeattend = $db->storeAttendanceData($tablename, $date, $atstatus, $rollnos); //insert data into attendance
		if(mysqli_query($Conn, $sql) == true){
			//show response in json format (JAVASCRIPT OBJECT NOTATION)
			$response["error"] = FALSE;
			$response["attendance"]["success"] = "Success";
			echo json_encode($response);
		}else{
			// failed to insert
			$response["error"] = TRUE;
			$response["error_msg"] = "failed to insert data";
			echo json_encode($response);
		}
		
	}else{
		//required parameters missing
		$response["error"] = TRUE;
		$response["error_msg"] = "Required parameters is missing!";
		echo json_encode($response);
	}

?>