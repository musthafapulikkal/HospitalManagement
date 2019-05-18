<?php
include("connection.php");
$hos_name=$_POST['hosname'];
$hos_place=$_POST['hosplace'];
$hos_contact=$_POST['hoscontact'];
$hos_state=$_POST['hosstate'];
$hos_dist=$_POST['hosdist'];
$hos_image=$_POST['hosimage'];
$target_dir = "upload/";
$target_dir = $target_dir."/".$hos_name.".JPEG";
    if(file_exists($target_dir)){
        unlink($target_dir);
    }
	$upload_image=$hos_name.".JPEG";
    if(file_put_contents($target_dir, base64_decode($hos_image))){
		$query="insert into tbl_hospital(hos_name,hos_place,hos_contact,hos_state,hos_district,hos_image)values('$hos_name','hos_place','$hos_contact','$hos_state','$hos_dist','$upload_image')";
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