<?php

	require_once ('Connect.php'); 
			
	//connection to database	
	$db = new Connect();
	$Conn = $db->connect();

	$response = array(); //array

	if(isset($_POST['tablename'])) {
		
		$tablename = $_POST['tablename'];
		
		$sql = "SELECT * FROM $tablename";
		$res = mysqli_query($Conn,$sql); //query in the table;
		while($row = mysqli_fetch_array($res)){
			array_push($response, array(
				"id" =>$row['id'],
				"chaptername"=>$row['chaptername']));//storing data in array;
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