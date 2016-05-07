<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.login != null}">
	<span>Logged in as ${sessionScope.login}</span>
	<br>
	<span><a href="/contacts/logout">Log out</a></span>
</c:if>
