function showOrgTree(){ 
	var selectedObj =windowsOpen("select_toOrgSelect");// window.showModalDialog("'select_toOrgSelect'",'','dialogWidth=500px;dialogHeight=350px;status=no;scroll=no;resizable=no;help=no;titlebar=no;location=no;menubar=no');
	if(selectedObj != null){ 
		document.getElementById('parentId').value=selectedObj.id;
		document.getElementById('parentName').value=selectedObj.name;
	
	if(selectedObj.orgTypeId==1){
		// var orgTypeId = $("select[@name=organization.orgType.id] option[@selected]").val();
		//  var orgTypeId = document.getElementById('orgTypeId').selectedIndex;   //orgTypeId
		// document.getElementById('orgTypeId').selectedIndex=1;
		// document.getElementById('followerId').value="";
		//	document.getElementById('followerName').value="";
	}else if(selectedObj.orgTypeId==2){
		// document.getElementById('orgTypeId').selectedIndex=2;
			document.getElementById('followerId').value=selectedObj.followerId;
			document.getElementById('followerName').value=selectedObj.followerName;
	}else{
		//	document.getElementById('followerId').value="";
		//	document.getElementById('followerName').value="";
		}	
	}
}
	
//
function toSystemUserSelect() {
	var orgTypeId =document.getElementById('orgTypeId').value ;
	if (orgTypeId != null && orgTypeId==3) {
		 alertMsg.info('零售店暂不能选择跟单员,只能根据上级代理商/经销商携带','');
	}else{
		openDlg('select_toSystemUserSelect','浏览用户');
	}  
}
	
	
var selectUserCount = 0;
//弹出页面返回处理操作
function _dialogUserReturnDo(selectedObj,obj) {
	if (selectedObj != null) {
	//	document.getElementById('parentName').value='00000';
	}  
}
 

function formChkAjaxToSubmit(obj){
	var parentVal=  document.getElementById('parentId').value;  
	var number= document.getElementById('number').value;
	var name= document.getElementById('name').value;
	// var orgTypeId = $("select[@name=organization.orgType.id] option[@selected]").val();
	//  var orgTypeId = document.getElementById('orgTypeId').selectedIndex;   //orgTypeId
	  var orgTypeId =document.getElementById('orgTypeId').value ;
	//  alert(orgTypeId);
	 // var   orgTypeTmp=  orgTypeId.options[orgTypeId.selectedIndex].text;   
	 if(number==null||number==""){
      	  alertMsg.info('组织编码不能为空','');
      	  return false;
     }
	 //大漠长风服饰级别的 可以不选择所属组织
	 if(orgTypeId==0){
		 
	 }else{
		 if(name==null||name==""){
	      	  alertMsg.info('组织名不能为空','');
	      	  return false;
	     }
        if(parentVal==null||parentVal==""){
             alertMsg.info('上级组织不能为空','');
             return false;
        } 
	 } 
    
    var organizationId=  document.getElementById('organizationId').value; 
    if(organizationId!=null&& organizationId!=""){//修改情况
    	if(orgTypeId==2){
    		return validateCallback(obj); 
    	}
    }
    	return validateCallback(obj);
}


function sumbitForm() {	
	if(checkform()){
		document.orgAddFrom.submit();
	}
}	

function  checkform() {   
	var number=$("input[@name=organization.number]").val();
    var name=$("input[@name=organization.name]").val();//用户名
	if(number==""){
		alert('请输入组织编码');
		$("input[@name=organization.number]").focus();
		return false;
	}
	if(name==""){
		alert('请输入代理商/经销商名称');
		$("input[@name=organization.name]").focus();
		return false;
	}
	
	var orgTypeID = $("select[@name=organization.orgType.id] option[@selected]").val();
	if(orgTypeID == 0){
		alert('请选择组织类型');
		return false;
	}
	if (follower == "" || follower == "0"){
		alert("请选择跟单员");
		return false;
	}
//	 
	
	return true;
}