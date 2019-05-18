<?php
include("connection.php");
$username=$_POST['username'];
$email=$_POST['email'];
$contact=$_POST['contact'];
$password=$_POST['password'];
$sql="insert into tbl_register(username,email,phone,password)values('$username','$email','$contact','$password')";
$res=mysqli_query($connect,$sql);
if($res)
{
	echo $email;
}
else
{
	echo "failed";
}


?>