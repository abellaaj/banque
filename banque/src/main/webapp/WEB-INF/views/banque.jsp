<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style1.css">
<title>Banque</title>
</head>
<body>	
	<div>
		<f:form method="post" action="chargerCompte" modelAttribute="banqueForm">
			<table>
				<tr>
					<td>Code:</td>
					<td><f:input path="code"/></td>
					<td><f:errors path="code" cssClass="error"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="OK"/></td>
				</tr>
			</table>
		</f:form>
	</div>	
	<c:if test="${banqueForm.compte != null}">
		<div>
			<table>
				<tr>
					<td>Solde:</td>
					<td>${banqueForm.compte.solde}</td>
				</tr>
				<tr>
					<td>Date Creation:</td>
					<td>${banqueForm.compte.dateCreation}</td>
				</tr>
				<tr>
					<td>Type Compte:</td>
					<td>${banqueForm.typeCompte}</td>
				</tr>
				<c:if test="${banqueForm.typeCompte == 'CompteCourant'}">
					<tr>
						<td>Découvert:</td>
						<td>${banqueForm.compte.decouvert}</td>
					</tr>
				</c:if>
				<c:if test="${banqueForm.typeCompte == 'CompteEpargne'}">
					<tr>
						<td>Taux:</td>
						<td>${banqueForm.compte.taux}</td>
					</tr>
				</c:if>
			</table>
		</div>
		<div>
			<table>
			<tr>
				<td>Nom Client:</td>
				<td>${banqueForm.compte.client.nomClient }</td>
			</tr>
			</table>
		</div>
		<div>
			<f:form action="saveOperation" modelAttribute="banqueForm">
			<f:hidden path="code"/>
			<table>
				<tr>
					<td>Versement: <f:radiobutton path="typeOperation" value="VER" onclick="this.form.submit()"/></td>
					<td>Retrait: <f:radiobutton path="typeOperation" value="RET" onclick="this.form.submit()"/></td>
					<td>Virement: <f:radiobutton path="typeOperation" value="VIR" onclick="this.form.submit()"/></td>
				</tr>
				<c:if test="${not empty banqueForm.typeOperation}">
					<tr>
						<td>Montant: </td>
						<td>
							<f:input path="montant"/>
							<f:errors path="montant" cssClass="error"></f:errors> 
						</td>
					</tr>
					<c:if test="${banqueForm.typeOperation == 'VIR'}">
						<tr>
							<td>Vers le compte : </td>
							<td>
								<f:input path="code2"/>
								<f:errors path="code2" cssClass="error" ></f:errors>
							</td>
						</tr>
					</c:if>
					<tr>
						<td>
							<input type="submit" name="action" value="save" >
						</td>
					</tr>
				</c:if>
			</table>
			</f:form>
		</div>
		<div>
			<table class="table1">
				<tr><th>Num</th><th>Type</th><th>Date</th><th>Montant</th></tr>
				<c:forEach items="${banqueForm.operations }" var="op">
					<tr>
						<td>${op.numeroOperation}</td>
						<td>${op}</td>
						<td>${op.dateOperation}</td>
						<td>${op.montant}</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<c:forEach begin="1" end="${banqueForm.nbrePages }" var="i" >
					<c:choose>
						<c:when test="${i==banqueForm.page }">
							<span class="current">page ${i }</span>	
						</c:when>
						<c:otherwise>
							<span class="autrePage">
								<a href="chargerCompte?page=${i }&code=${banqueForm.code}">page ${i }</a>
							</span>
						</c:otherwise>
					</c:choose>	
					
				</c:forEach>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty banqueForm.exception}">
		<div>
			${banqueForm.exception}
		</div>
	</c:if>
</body>
</html>