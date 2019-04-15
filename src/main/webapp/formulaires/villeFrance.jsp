<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en">
<body>
    <div>
        <div>
        	<form>
	        	<select name="villeFrance">
					<c:forEach var="i" begin="0" end="${villeFrance.size()}" step="1">
						<option value="${villeFrance.get(i).getCodePostal()}">
						${villeFrance.get(i).getCodePostal()}
						</option>
					</c:forEach>
				
				</select>
        	</form>
        	<c:forEach var = "i" begin = "1" end = "5">
         			Item <c:out value = "${i}"/><p>
      		</c:forEach>
        </div>
    </div>
</body>
</html>