<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="operator.kmeans" %>
<%! ArrayList<double[]> dataArray = new ArrayList<double[]>(); %> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>电力用户画像聚类</title>
    <link rel="stylesheet" href="css/style.css">
  	<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/function.js"></script>
    
</head>
<body onload="load();">   
<div id="loading"><img src="img/load.gif" alt=""/>正在加载数据,请稍候...</div>
<div class ="title" style = "height:200px;width:3000px">
	<div class = "i" style ="height:200px;width:500px;float:left">
		<div class = "input" style="margin-left:50px;margin-top:20px">
	
	<div class="file-box"> 
		<form id="uploadForm"> 
			<input type="text" id="textfield" class="txt" />
			<input type="button" class="btn" value="csv" /> 
			<input type="file" name="file" class="file" id="fileField" onchange="document.getElementById('textfield').value=this.files[0].name"/> 
			<input type="button" class="btn" value="上传"  id = "filebtn" /> 
		</form> 
	</div>
	<div class="file-box" > 
		<form id="uploadForm"> 
			<input type="text" id="textfield2" class="txt" />
			<input type="button" class="btn" value="zip" /> 
			<input type="file" name="file" class="file" id="fileField" onchange="document.getElementById('textfield2').value=this.files[0].name"/> 
			<input type="button" class="btn" value="上传"  id = "filebtn2" /> 
		</form> 
	</div>
	</div>
	
	<div class="function-box" style="margin-left:50px">
	    <form id="form1"  method="post" action = "Getdata">
	      <input type="button" class ="btn" value="聚类" id="btn"/> 
	      <input type="button" class ="btn" value="获取" id="btn2"/>
	      <input type="button" class ="btn" value="newclass" id="btn5"/>
	      <input type="button" class ="btn" value="FCM" id="btn4"/>
	      <input type="button" class ="btn" value="保存数据" id="btn6"/>
	    <ul id="ul1"></ul>
	    </form>
	</div>
	</div>
	<div class = "show"  id ="show" style ="width:800px;height:200px;float:left">
	</div>
	<div class = "all-user" id = "all-user"  style = "weight:200px;height:200px;float:left;margin-top: 20px">
	<div style = "width:200px;height:40px ">
		已添加用户名单
	    <select id = "select">
	    </select>
    </div>
	<div style = "width:200px;height:40px">
		<input type="button" class ="info" value="获取信息" id="info"/> 
	    <input type="button" class ="info" value="删除用户信息" id="delete"/> 
	</div>
    <div>
       Start Time:<br>
       <input type="text" name="startday"  id = "start" placeholder="xxxx-xx-xx">
       <br>
       End Time:<br>
       <input type="text" name="endday"  id = "end" placeholder="xxxx-xx-xx">
       <br>
       <input type="button" class ="info" value="查询" id="selectdays" /> 
    </div>
	</div>	
</div>
<div style = "height :700px;width:2000px">
    <div id="main3" class="origin_data" style="height:600px;width:1000px;margin:50px;float:left"></div>   
<div class = "user" style = "height:600px;width:700px;float:left">
    <div id="main2" class="user_mode" style="height:250px;width:500px;margin-top:50px;margin-bottom:50px"></div>
    <div id="main1" class="all_mode"  style="height:250px;width:500px;margin:50px 50px 50px 0px;" ></div>
</div>
</div>
<div>
<div id="main4" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
<div id="main5" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
<div id="main6" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
<div id="main7" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
<div id="main8" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
<div id="main9" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
<div id="main10" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
<div id="main11" class="fuzzy_class"  style="height:300px;width:600px;margin-left:50px" ></div>
</div>

     <script src="js/echarts.js"></script>
     <script type="text/javascript" src = "js/initechart.js">
     
                     
    </script>

    
</body>
</html>