<?php
include("connection.php");
$hosemail=$_POST['hosemail'];
$doc_name=$_POST['docname'];
$doc_specialist=$_POST['docspcl'];
$doc_holi=$_POST['docholi'];
$doc_image=$_POST['docimg'];
$sql="select * from tbl_hospital_reg where hos_reg_email='$hosemail'";
$exe=mysqli_query($connect,$sql);
if (mysqli_num_rows($exe)>0) 
{
	$row=mysqli_fetch_assoc($exe);
	$id=$row['hos_reg_id'];
	$target_dir = "doctors/";
	$target_dir = $target_dir."/".$doc_name.".JPEG";
    if(file_exists($target_dir)){
        unlink($target_dir);
    }
	$upload_image=$doc_name.".JPEG";
    if(file_put_contents($target_dir, base64_decode($doc_image))){
		$query="insert into tbl_doctors(hos_id,doc_name,doc_specialist,doc_holi,doc_image)values('$id','$doc_name','$doc_specialist','$doc_holi','$upload_image')";
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