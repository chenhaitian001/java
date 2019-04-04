g	var userGroupsArray = new Array();//保存选择的商品数组
	function cancel1(){
		this.$("div.dialog").hide();
		this.$("div.shadow").remove();
	}
	
	function submitDialog(){
		var ckb_chkIds = $.pdialog.getCurrent().find("input:[name=UserGroupchkIds]"); 
		alert(ckb_chkIds);
		var checkedCount = 0;
		for(var i = 0; i < ckb_chkIds.length; i++){
			if(ckb_chkIds[i].checked){
				checkedCount++;
			}
		}
		if(checkedCount == 0){
			alert('请选择行');
			return false;
		}
		//window.returnValue=userGroupsArray;
		//window.close();
		this.$("div.dialog").hide();
	    this.$("div.shadow").remove();
	    _dialogMultiUserGroupReturnDo(userGroupsArray,this);//调用提交页面返回操作
	}
	// 动态数组
	Array.prototype.append = function(Obj) {
		var newArr = new Array(Obj);
		return this.concat(newArr);
	};

	Array.prototype.remove = function(Obj) {
		var retArr = new Array();
		for(var i = 0; i < this.length; i++) {
			if(this[i].fid != Obj.fid) retArr = retArr.append(this[i]);
		}
		return retArr;
	};
	Array.prototype.removeAll = function(){
		while(this.length>0){
			this.pop();
		}
	}

	Array.prototype.hasItem = function(Obj) {
		for(var i = 0; i < this.length; i++) {
			if(this[i].fid == Obj.fid) {
				return true;
			}
		}
		return false;
	};

	Array.prototype.getItem=function(id){
		for(var i = 0; i < this.length; i++) {
			if(this[i].fid == id) {
				return this[i];
			}
		}
		return null;
	}

	Array.prototype.insert = function(index,obj){
		var retArr = new Array(this.length+1);
		retArr[index]=obj;
		for(var i=0,j=0;i<this.length;i++,j++){
			if(i==index){
				j++;
			}
			retArr[j]=this[i];
		}
		return retArr;
	}
	function selectedUserChk(obj,id,number,name,k){
		if (k>0) { userGroupsArray = new Array(); }
		obj=document.getElementById('id'+id);
	 	var userObj = new Object();
	 	userObj.id = id;
    	if(obj.checked)
        {
	 	userObj.id=id;
		userObj.number=number;
	 	userObj.name=name;
	 	userGroupsArray = userGroupsArray.append(userObj);
	    }else{
	    	userGroupsArray = userGroupsArray.remove(userObj); 
	   }
    }
	
	function querySubmit(){
		$("#pagerSelectcurrentPage").val(1);
		$("#pagerSelectForm").submit();
		
	}