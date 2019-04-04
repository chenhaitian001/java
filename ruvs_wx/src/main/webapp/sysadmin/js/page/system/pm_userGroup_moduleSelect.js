	function updatePermission() {
		var listId = tree.getAllCheckedBranches();
		if (!chkIsSelect()) {
			alert("请选择用户组");
			return;
		}else {
			$("#moduleSelectIds", navTab.getCurrentPanel()).val(listId);
			document.getElementById('btnSubmitGroupPm').click();  //$("#btnSubmit", navTab.getCurrentPanel()).click();
		}
	}

	function queryUserPage() {
		if (!chkIsSelect()) {
			alert("请选择用户组");
			return;
		} else {
			$("#userGroupPmtForm", navTab.getCurrentPanel()).attr("action","pm_toUserGroupModuleSelect");
			$("#btnSubmitGroupPm", navTab.getCurrentPanel()).click();
		}
	}

	
	//弹出页面返回处理操作
	function _dialogUserGroupReturnDo(selectedObj,obj) {
		if (selectedObj != null) {
			clearAll();
			$("#selRelId", navTab.getCurrentPanel()).val(selectedObj.id);
			$("#selRelName", navTab.getCurrentPanel()).val(selectedObj.name);
			queryUserPage();
		}
	}
	
	

	function clearAll() {
		$("#selRelId", navTab.getCurrentPanel()).val("");
		$("#selRelName", navTab.getCurrentPanel()).val("");
	}

	function chkIsSelect() {
		var t1 = $("#selRelId", navTab.getCurrentPanel()).val();
		if (t1 != null && t1 != "") {
			//selRelId = t1;
			return true;
		}
	}

	function initDTreeItems() {
		tree.closeAllItems(2);
		tree.closeAllItems(3);
		tree.closeAllItems(4);
		tree.closeAllItems(5);
	}
	
	