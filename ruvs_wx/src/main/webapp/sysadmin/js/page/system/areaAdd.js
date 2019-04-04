function checkSevenAreaNumber(){
	var number= document.getElementById('number').value;
	var name= document.getElementById('name').value;
	 if(number==null||number==""){
      	  alertMsg.info('区域编码不能为空','');
      	  return false;
     }
	 if(name==null||name==""){
      	  alertMsg.info('区域名不能为空','');
      	  return false;
     }
	document.pagerForm.action="area_checkNumberExist";
	document.getElementById('btnSubmit').click(); 
}