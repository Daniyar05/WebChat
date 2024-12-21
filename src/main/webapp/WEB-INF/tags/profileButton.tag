<%@ tag description="Profile Button Tag" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/tag.css">
<ul>
    <button onclick="location.href='profile'">
        <img src="${pageContext.request.contextPath}/avatars?userId=${userId}"
             alt="avatar"
             style="width: 40px; height: 40px; border-radius: 50%; object-fit: cover;">
        ${user.getNickname()}
    </button>
</ul>
