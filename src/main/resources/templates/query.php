<?php

$servername = "localhost";
$username = "root";
$password = "IceCream";
$dbname = "weight";


$output = array( 'success' => false, 'error' => null, 
                 'resp' => array() );
// Create connection
$conn = new mysqli($servername, $username, 
              $password, $dbname);

// Check connection
if ($conn->connect_error) {
die("Connection failed: " . $conn->connect_error);
}


$databaseTableName = "weight_entry";
$sql = "SELECT date, weight 
FROM $databaseTableName";
$result = $conn->query($sql);

if ( $result->num_rows > 0) 
{
     $output["success"] = true;

     // output data of each row
     while( $row = $result->fetch_assoc()) {
          $output["resp"] = $row;
          break;
      }
}
else {
      $output["error"] = "No rows selected";
}
$conn->close();
echo json_encode($output);
print(json_encode($output);)
?>