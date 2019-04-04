	function updatePermission() {
		var listId = tree.getAllCheckedBranches();
		if (!chkIsSelect()) {
			alert("请选择用户"); 
			return;
		} 
		else {
			$("#moduleSelectIds", navTab.getCurrentPanel()).val(listId);
			$("#btnSubmit", navTab.getCurrentPanel()).click(); 
		} 
	}
	
	//弹出页面返回处理操作
	function _dialogUserReturnDo(selectedObj,obj) {
		if (selectedObj != null) {
			clearAll();
			$("#selRelId", navTab.getCurrentPanel()).val(selectedObj.id);
			$("#selRelName", navTab.getCurrentPanel()).val(selectedObj.name);
			$("#selRoleId", navTab.getCurrentPanel()).val(selectedObj.roleId);
			queryUserPage();
		}
	}

	function queryUserPage() {
		if (!chkIsSelect()) { 
			alert("请选择用户");
			return;
		} else {
			$("#pagerForm", navTab.getCurrentPanel()).attr("action","pm_toUserModuleSelect");
			$("#btnSubmit", navTab.getCurrentPanel()).click(); 
		}
	}

	function clearAll() {
		$("#selRelId", navTab.getCurrentPanel()).val("");
		$("#selRelName", navTab.getCurrentPanel()).val("");
		$("#selRoleId", navTab.getCurrentPanel()).val("");
	}

	function chkIsSelect() {
		var t1 = $("#selRelId", navTab.getCurrentPanel()).val();;
		if (t1 != null && t1 != "") {
			return true;
		}
	}

	function initDTreeItems() {
		var tmpSelRelId= $("#selRelId", navTab.getCurrentPanel()).val();
		tree.closeAllItems(tmpSelRelId);
	}
	