function showOrgTree(action){
	//弹出打开组织树
	var selectedObj =windowsOpen("select_toOrgSelectNoTopSel");
	//alert(action);
	alert(selectedObj);
	if(selectedObj != null){
		$("#selRelId", navTab.getCurrentPanel()).val(selectedObj.id);
		$("#selRelName", navTab.getCurrentPanel()).val(selectedObj.name);
		queryPage(action); 
	}
}


function queryPage(action) {
	if (!chkOrgIsSelect()) {
		alert("请选择组织");
		return;
	} else {
		$("#pagerForm", navTab.getCurrentPanel()).attr('action',action);
		alert($("#pagerForm", navTab.getCurrentPanel()).attr("action"));
		//$("#isPage", navTab.getCurrentPanel()).val(0);
		$("#btnSubmit", navTab.getCurrentPanel()).click();
	}
}

function chkOrgIsSelect() {
	var t1 = $("#selRelId", navTab.getCurrentPanel()).val();
	//alert(t1);
	if (t1 != null && t1 != "") {
		//selRelId = t1;
		return true;
	}
}
