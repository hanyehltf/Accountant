<?php

    $json=file_get_contents('php://input');
    $userInfo=json_decode($json);
    $email=$userInfo->email;
    $password=$userInfo->password;

    include 'DataBaseManager.php';
    $databaseManager=new DataBaseManager();
  $response= $databaseManager->loginUser($email,$password);
   echo json_encode(['response'=>$response]);

   
