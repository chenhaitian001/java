<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body style="width:1920px">
	<div>
		<style type="text/css">
#tl td,#tb td,#tb th {
	padding: 5px;
	border-top: 1px solid #000;
	border-left: 1px solid #000;
	font-size: 12px;
}

#tl,#tb {
	border-bottom: 1px solid #000;
	border-right: 1px solid #000;
	position: absolute;
	right: 0px;
	bottom: 5px;
}

#tb {
	position: inherit;
}

* {
	font-size: 12px;
}
</style>

		<h1 style="text-align: center;font-size:34px;">员工出勤统计表</h1>
		<div style="position: relative;width:100%;height:50px;">
			<table cellspacing="0" cellpadding="0" id="tl">
				<tr>
					<td rowspan="2">符号</td>
					<td>出勤</td>
					<td>√</td>
					<td>病假</td>
					<td>+</td>
					<td>公休</td>
					<td>休</td>
					<td>婚假</td>
					<td>婚</td>
					<td>探亲</td>
					<td>0</td>
					<td>丧假</td>
					<td>S</td>
					<td>迟到</td>
					<td>迟</td>
				</tr>
				<tr>
					<td>事假</td>
					<td>△</td>
					<td>旷工</td>
					<td>x</td>
					<td>年休假</td>
					<td>H</td>
					<td>产假</td>
					<td>产</td>
					<td>护理假</td>
					<td>护</td>
					<td>工伤</td>
					<td>#</td>
					<td>早退</td>
					<td>退</td>
				</tr>
			</table>

			<table style="position: absolute;left:200px;bottom:5px;">
				<tr>
					<td>供电所:</td>
					<td>${bumen }&nbsp;</td>
					<td>时间:</td>
					<td>&nbsp;${year }年&nbsp;${month }月</td>
				</tr>
			</table>

		</div>
		<table cellspacing="0" cellpadding="0" id="tb" style="width: 100%">
			<tr>
				<th rowspan="2">序号</th>
				<th rowspan="2">姓名</th>
				<th rowspan="2">出<br /> 勤<br /> 日<br /> 数</th>
				<th rowspan="2">年<br />休<br />日<br />数</th>
				<th rowspan="2">调<br />休<br />日<br />数</th>
				<th colspan="10">缺勤日数</th>
				<th>迟到早<br />退次数</th>
				<th colspan="31">出勤日期</th>
			</tr>
			<tr>
				<th>旷工</th>
				<th>工伤</th>
				<th>事假</th>
				<th>丧假</th>
				<th>探亲</th>
				<th>病假</th>
				<th>婚假</th>
				<th>产假</th>
				<th>护理假</th>
				<th>合计</th>
				<th>合计</th>
				<th>1</th>
				<th>2</th>
				<th>3</th>
				<th>4</th>
				<th>5</th>
				<th>6</th>
				<th>7</th>
				<th>8</th>
				<th>9</th>
				<th>10</th>
				<th>11</th>
				<th>12</th>
				<th>13</th>
				<th>14</th>
				<th>15</th>
				<th>16</th>
				<th>17</th>
				<th>18</th>
				<th>19</th>
				<th>20</th>
				<th>21</th>
				<th>22</th>
				<th>23</th>
				<th>24</th>
				<th>25</th>
				<th>26</th>
				<th>27</th>
				<th>28</th>
				<th>29</th>
				<th>30</th>
				<th>31</th>
			</tr>

			<c:forEach items="${list}" var="item" varStatus="s">
				<tr>
					<td>${item.num }</td>
					<td>${item.name }</td>
					<td align="right">${item.cqt }</td>
					<td align="right">${item.nxt }</td>
					<td align="right">${item.txt }</td>
					<td align="right">${item.kgt }</td>
					<td align="right">${item.gst }</td>
					<td align="right">${item.shjt }</td>
					<td align="right">${item.sjt }</td>
					<td align="right">${item.tqt }</td>
					<td align="right">${item.bjt }</td>
					<td align="right">${item.hjt }</td>
					<td align="right">${item.cjt }</td>
					<td align="right">${item.hlt }</td>
					<td align="right">${item.hlt+ item.cqt + item.nxt + item.txt + item.kgt +
						item.gst + item.shjt + item.sjt + item.tqt + item.bjt + item.hjt +
						item.cjt }</td>
					<td align="right">${item.cdztt }</td>
					<td align="center">${item.day_01}</td>
					<td align="center">${item.day_02}</td>
					<td align="center">${item.day_03}</td>
					<td align="center">${item.day_04}</td>
					<td align="center">${item.day_05}</td>
					<td align="center">${item.day_06}</td>
					<td align="center">${item.day_07}</td>
					<td align="center">${item.day_08}</td>
					<td align="center">${item.day_09}</td>
					<td align="center">${item.day_10}</td>
					<td align="center">${item.day_11}</td>
					<td align="center">${item.day_12}</td>
					<td align="center">${item.day_13}</td>
					<td align="center">${item.day_14}</td>
					<td align="center">${item.day_15}</td>
					<td align="center">${item.day_16}</td>
					<td align="center">${item.day_17}</td>
					<td align="center">${item.day_18}</td>
					<td align="center">${item.day_19}</td>
					<td align="center">${item.day_20}</td>
					<td align="center">${item.day_21}</td>
					<td align="center">${item.day_22}</td>
					<td align="center">${item.day_23}</td>
					<td align="center">${item.day_24}</td>
					<td align="center">${item.day_25}</td>
					<td align="center">${item.day_26}</td>
					<td align="center">${item.day_27}</td>
					<td align="center">${item.day_28}</td>
					<td align="center">${item.day_29}</td>
					<td align="center">${item.day_30}</td>
					<td align="center">${item.day_31}</td>
				</tr>
			</c:forEach>

		</table>

		<div style="position: relative;">
			<span
				style="display: inline-block;position: absolute;left:200px; top:50px">供电所所长：</span>
			<span
				style="display: inline-block;position: absolute;right:300px; top:50px">考勤员：</span>
		</div>
	</div>
</body>
</html>