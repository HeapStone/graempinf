<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../commonresource.jsp" %>
<script type="text/javascript">
var mt;
$(function(){
	  var opt={url:'orgsTreeAll',mine:{expandAll:false,listChild:'1',src:"orgs/",srcElement:"#cc",}};
	mt = $("#tree").mytree(opt);
});
function refreshTree() {
	mt.reAsyncChildNodes(null,"refresh");
}
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<span>正在使用栏目管理功能</span>
	</h3>
	<TABLE border=0 align=left height="600px">
		<TR>
			<TD width=150px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:150px; overflow:auto;"></ul>
			</TD>
			<TD width=1024px align="right" valign=top><IFRAME ID="cc" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=500px ></IFRAME></TD>
		</TR>
	</TABLE>
</div>
</body>
</html>