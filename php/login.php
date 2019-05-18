<?php
include("connection.php");
$type=$_GET['type'];
if ($type=='user') {
$email=$_POST['email'];
$password=$_POST['password'];
$sql="select email,password from tbl_register where email='$email' and password='$password'";
$res=mysqli_query($connect,$sql);
if (mysqli_num_rows($res)>0) {
	echo $email;
}
else
{
	echo "failed";
}
}
elseif ($type=='hospital') 
{
	$email=$_POST['email'];
	$password=$_POST['password'];
	$sql="select hos_reg_email,hos_reg_password from tbl_hospital_reg where hos_reg_email='$email' and hos_reg_password='$password' and status=1";
	$res=mysqli_query($connect,$sql);
	if (mysqli_num_rows($res)>0) {
	echo $email;
	}
	else
	{
	echo "failed";
	}
}
else
{
	echo "failed";
}


?>