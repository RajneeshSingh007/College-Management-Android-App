<?php

	require_once ('Connect.php'); 
			
	//connection to database	
	$db = new Connect();
	$Conn = $db->connect();
	
	
	if(isset($_GET['email']) && !empty($_GET['email']) AND isset($_GET['hash']) && !empty($_GET['hash'])){
		$email = $_GET['email']; // Set email variable
		$hash = $_GET['hash']; // Set hash variable	
		// Verify data
		$search = mysqli_query($Conn,"SELECT email, hash, active FROM teacher WHERE email='".$email."' AND hash='".$hash."' AND active='0'"); 
		$match  = mysqli_num_rows($search);		
		if($match > 0){
			echo "Successfully Verified";
			mysqli_query($Conn, "UPDATE teacher SET active='1' WHERE email='".$email."' AND hash='".$hash."' AND active='0'");
		}else{
			//account has already been activated.
			echo "Already verified";
		}	

	}else{
		// Invalid approach
		echo "Invalid approach";
	}

?>