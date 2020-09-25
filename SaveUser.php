<?php

    $json=file_get_contents('php://input');
    $userInfo=json_decode($json);
    $email=$userInfo->Email;
    $password=$userInfo->Password;
$BusinessName=$userInfo->BusinessName;
$BusinessType=$userInfo->BusinessType;
    include 'DataBaseManager.php';
    $databaseManager=new DataBaseManager;
    $response=$databaseManager->AddUsers($email,$password,$BusinessName,$BusinessType);
    echo json_encode(['response'=>$response]);
