<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common.jsp"%>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
    <script type="text/javascript">
        $.parser.parse();
    </script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" id="layout_home">
    <div id="north" data-options="region:'north',split:false,border:false" style="height: 48px; padding: 0px;">
        <jsp:include page="home_north.jsp"/>
    </div>
    <div data-options="region:'west',split:true,border:true,collapsed:false,hideCollapsedContent:false,title:' '" style="width: 250px; padding: 0px;">
        <jsp:include page="home_west.jsp"/>
    </div>
    <div data-options="region:'center',split:true,border:false" style="padding: 0px;">
        <jsp:include page="home_center.jsp"/>
    </div>
    <div data-options="region:'east',split:true,border:true,collapsed:false,hideCollapsedContent:true,title:' '" style="width: 250px; padding: 0px;">
        <jsp:include page="home_east.jsp"/>
    </div>
    <div data-options="region:'south',split:false,border:false" style="height: 24px; padding: 0px;">
        <jsp:include page="home_south.jsp"/>
    </div>
</div>
</body>
</html>