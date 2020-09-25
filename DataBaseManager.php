<?php 




/**
*creat DataBase
*User Haniyeh Lotfi
*9/5/2020

 */

 class DataBaseManager{

const DATABASENAME="Accountent";




function creatDataBase(){
$connection=mysqli_connect("localhost","root","");
$sqli_command="CREATE DATABASE ".DataBaseManager::DATABASENAME;
if(mysqli_query($connection,$sqli_command)){
echo "DataBase Create Successfully";

}
}


function CreateUsersTAble(){
    $connection=mysqli_connect("localhost","root","",DataBaseManager::DATABASENAME);
    $sqli_command="CREATE TABLE users(id INTEGER PRIMARY KEY AUTO_INCREMENT,
    Email TEXT ,
    password TEXT,
    BusinessName  TEXT,
     BusinessType TEXT)";
     if(mysqli_query($connection,$sqli_command)){
    
        echo "Table Create Successfuly";
     }
  
    }



    function AddUsers($Email,$Password,$BusinessName,$BusinessType){

if($this->isUserEmailExist($Email))
{return 2;}
if(empty($Email)){
    return 0;
}






$connection=mysqli_connect("localhost","root","",DataBaseManager::DATABASENAME);
$sqli_command="INSERT INTO users(Email,password,BusinessName,BusinessType)VALUES('$Email','$Password','$BusinessName','$BusinessType')";
if(sqli_query($connection,$sqli_command)){
    echo"inset into users table was successfuly";
    return 1;
}else{
    echo "Error in adding post to table";
return 0;
}
    }






function loginUser($email,$Password){
    $connection = mysqli_connect("localhost", "root", "", DataBaseManager::DATABASENAME);
    $sqlquery="SELECT * FROM users WHERE Email = '$email'  AND  password = '$Password' ";

    $result=$connection->query($sqlquery);
  
    if ($result->num_rows>0){


if($row=$result->fetch_assoc()){


    return $row;



}


 }

}


function isUserEmailExist($email){
    $connection = mysqli_connect("localhost", "root", "", DataBaseManager::DATABASENAME);
    $sqlCommand="SELECT * FROM users WHERE Email = '$email'";
    $result=mysqli_query($connection,$sqlCommand);
    if ($result->num_rows>0){
        return true;
    }else{
        return false;
    }
}
 }


 ?>