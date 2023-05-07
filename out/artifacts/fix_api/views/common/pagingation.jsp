<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="pagination jc-center" id="pagination">
  <c:set var="currentPage" value="${pageable.getPage()}"/>
  <c:forEach begin="1" end="${pageable.getTotalPages()}" varStatus="loop">
    <li class="page-item active">
      <a class="page-link" href="${home}${loop.index + ""}">${loop.index}</a>
    </li>
  </c:forEach>
</ul>