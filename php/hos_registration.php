<?php
include("connection.php");
$name=$_POST['name'];
$place=$_POST['place'];
$email=$_POST['email'];
$state=$_POST['state'];
$district=$_POST['district'];
$password=$_POST['password'];
$contact=$_POST['contact'];
$image=$_POST['image'];
$target_dir = "upload/";
$target_dir = $target_dir."/".$name.".JPEG";
    if(file_exists($target_dir)){
        unlink($target_dir);
    }
	$upload_image=$name.".JPEG";
    if(file_put_contents($target_dir, base64_decode($image))){
		$query="insert into tbl_hospital_reg(hos_reg_name,hos_reg_email,hos_reg_place,hos_reg_district,hos_reg_state,hos_reg_contact,hos_reg_password,hos_image,status)values('$name','$email','$place','$district','$state','$contact','$password','$upload_image',0)";
		$result=mysqli_query($connect,$query);
	if($result)
	{
	echo("success");
	}
	else
	{
		"invalid";
	}
}
?>