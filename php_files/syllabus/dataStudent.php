<?php

	require_once ('Connect.php'); 
			
	//connection to database	
	$db = new Connect();
	$Conn = $db->connect();

	$response = array(); //json array response

	if(isset($_POST['tablename'])) {
		
		$tablename = $_POST['tablename'];
		
		$sql = "SELECT * FROM $tablename WHERE workingdata = 1";
		$res = mysqli_query($Conn,$sql); //query in the table;
		while($row = mysqli_fetch_array($res)){
			array_push($response, array(
				"id" =>$row['id'],
				"chaptername"=>$row['chaptername'],
				"checked"=>$row['checked'])
			);//storing data in array;
		}
		echo json_encode($response);
		mysqli_close($Conn);
	}else{
		//Invalid approach 
		$response["error"] = TRUE;
		$response["error_msg"] = "Invalid approach!";
		echo json_encode($response);
	}
?>