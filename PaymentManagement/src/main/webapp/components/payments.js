$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validatePaymentForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // if hidPaymentIDSave value is null set as POST else set as PUT
    var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";

    // ajax communication
    $.ajax({
        url: "PaymentsAPI",
        type: type,
        data: $("#formPayment").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onPaymentSaveComplete(response.responseText, status);
        }
    });
});

// after completing save request
function onPaymentSaveComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully saved");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divPaymentsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while saving");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while saving");
        $("#alertError").show();
    } 

    //resetting the form
    $("#hidPaymentIDSave").val("");
    $("#formPayment")[0].reset();
}

// UPDATE
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 
    //get item id from the data-itemid attribute in update button
    $("#hidPaymentIDSave").val($(this).data('paymentid')); 
    //get data from <td> element
    $("#billNo").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#cardNo").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#expiryDate").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#securityNo").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

// DELETE
$(document).on("click",".btnRemove", function(event) {
    // ajax communication
    $.ajax({
        url: "PaymentsAPI",
        type: "DELETE",
        data: "paymentID=" + $(this).data("paymentid"),
        dataType: "text",
        complete: function(response, status) {
            onPaymentDeleteComplete(response.responseText, status);
        }
    });
});

// after completing delete request
function onPaymentDeleteComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully deleted");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divPaymentsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while deleting");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while deleting");
        $("#alertError").show();
    } 
}

// VALIDATION
function validatePaymentForm() { 
    // Bill No 
    if ($("#billNo").val().trim() == "") 
    { 
        return "Insert Bill No."; 
    } 
    
    // Card No 
    if ($("#cardNo").val().trim() == "") 
    { 
        return "Insert Card No."; 
    } 
    
    // Expiry Date
    if ($("#expiryDate").val().trim() == "") 
    { 
        return "Insert Expiry Date."; 
    } 
    
    // Security No
    if ($("#securityNo").val().trim() == "") 
    { 
        return "Insert Security No."; 
    } 
    
    return true; 
} 
 