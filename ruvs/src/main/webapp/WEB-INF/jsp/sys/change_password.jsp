<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form id="_form"   method="POST">
		<ul class="edit_area">
			<li>
				<span class="text">旧密码</span>
				<span class="value">
					<input type="password" name="old_password" class="easyui-textbox" data-options="required:true,validType:'length[2,50]'" />
				</span>
			</li>
			<li>
				<span class="text">新密码</span>
				<span class="value">
					<input type="password" name="new_password" class="easyui-textbox" data-options="required:true,validType:'length[2,50]'"/>
				</span>
			</li>
		</ul>
	</form>