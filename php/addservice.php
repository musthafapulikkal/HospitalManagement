<?php
include("connection.php");
$hosemail=$_POST['hos_email'];
$caption=$_POST['service_caption'];
$description=$_POST['service_description'];
$image=$_POST['service_image'];
$sql="select * from tbl_hospital_reg where hos_reg_email='$hosemail'";
$res=mysqli_query($connect,$sql);
if (mysqli_num_rows($res)>0) {
	$row=mysqli_fetch_assoc($res);
	$id=$row['hos_reg_id'];
	$target_dir = "services/";
	$target_dir = $target_dir."/".$caption.".JPEG";
    if(file_exists($target_dir)){
        unlink($target_dir);
    }
	$upload_image=$caption.".JPEG";
    if(file_put_contents($target_dir, base64_decode($image))){
		$query="insert into tbl_services(hos_id,caption,description,image)values('$id','$caption','$description','$upload_image')";
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
}

?>