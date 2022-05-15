<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/payments.js"></script>
		<title>Payments Management</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>Payments Management</h1>
					<form id="formPayment" name="formPayment" method="POST" action="payments.jsp">
						Bill No: 
						<input 
							id="billNo" 
							name="billNo" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Card No: 
						<input 
							id="cardNo"
							name="cardNo" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Expiry Date: 
						<input 
							id="expiryDate" 
							name="expiryDate" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Security No: 
						<input 
							id="securityNo" 
							name="securityNo" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						<input 
							id="btnSave" 
							name="btnSave" 
							type="button" 
							value="Save" 
							class="btn btn-primary"
						>

						<input type="hidden" name="hidPaymentIDSave" id="hidPaymentIDSave" value="">
					</form>
				
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<div id="divPaymentsGrid">
						<%
							Payment payment = new Payment(); 
							out.print(payment.readPayments());
						%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>