<%@ tag description="Profile Button Tag" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/tag.css">
<ul>
    <button onclick="location.href='profile'">
        <img src="${pageContext.request.contextPath}/avatars?userId=${user.getId()}"
             alt="avatar"
             style="width: 40px; height: 40px; border-radius: 50%; object-fit: cover;">
        ${user.getNickname()}
    </button>
</ul>

<%--<a href="${pageContext.request.contextPath}/profile" class="profile-button">--%>
<%--    <img src="https://static-00.iconduck.com/assets.00/chat-icon-2048x2048-i7er18st.png" alt="Profile" class="profile-icon"/>--%>
<%--    <span class="profile-text">Profile</span>--%>
<%--</a>--%>
