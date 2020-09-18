  var status = false;
     var xml_data = {"data1":"2","data2":"3","data3":"4"};
     var use_data = {"data":" "};
    $(function(){
        $("#filebtn").click(function(){
        	//alert("上产文件");
        	//var filename=document.getElementById('fileField').value;
        	//document.getElementById('textfield2').value='';
        	var filename=document.getElementById('textfield').value;
        	var file = {"name":filename,"data1":"2"}; 
        	var f = filename.split(".")[0];
        	var sel=document.getElementById("select");
        	var option = new Option(f,f);
        	sel.add(option);
        
        	  $.ajax({
        		  async: false,
        		  beforeSend: function () {
              	    ShowDiv();
              	},
              	  complete: function () {
              	    HiddenDiv();
              	},          	  
                  url: "loadfile",
                  type:"POST",//请求方式POST
                  data:file,//传过去后台的json 这里注意前面加一个"json1="
                  dataType:"json", //预期从服务器中接受的数据类型
                  error:function(XMLHttpRequest, textStatus, errorThrown){
                      alert(XMLHttpRequest);
                      alert(textStatus);
                      alert(errorThrown);
                  },
                  success:function(data){
                	  //alert(data["datArrayList"]);
                      //alert(data["startday"]);
                      var tmp = [];
                      for(var i =0; i< data["datArrayList"].length-1;i++){
                      	temp = {
                      		symbol:"none",
                      		name:i,
                      	    type:"line",
                      	    data:data["datArrayList"][i]
                      	};
                      tmp.push(temp);
                      }
                      var option = {
		                      // 定义样式和数据
		                      backgroundColor: '#E5E5E5',//背景色
		                       title: {
		                       text: '用户96点用电数据',
		                       subtext :'时间段：'+data["startday"] +'-'+ data["endday"],
		                       subtextStyle:{
		                     	  color:'black'
		                       },
		                       left: 'center'
		                      },
		                      
		                      tooltip: {
		                          trigger: 'axis'
		                      },
		                      legend: {
		                          data: ['数据1']
		                      },
		               
		                      calculable: true,
		                      xAxis: [{
		                      	type: "category",
		                          axisLabel: {
		                          	 interval:4,
		                          	textStyle:{
		                  				color:'1a1a1a',  //坐标的字体颜色
		                  			}
		                          },
		                          axisLine: {
		                              lineStyle: {
		                                  color: '1a1a1a'
		                              }
		                          },
		                          data: ["00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"]
		                   
		                        }],
		                      yAxis: [{  
		                          type: 'value',
		                          axisLabel: {
		                          	textStyle:{
		                  				color:'1a1a1a',  //坐标的字体颜色
		                  			}
		                          },
		                          axisLine: {
		                              lineStyle: {
		                                  color: '1a1a1a'
		                              }
		                          }
		                      }],
		                      series:tmp
		                  };

		                  // 使用刚指定的配置项和数据显示图表。
		                  myChart3.setOption(option,true);
                     
                      var tmp = [
                    	  {value:data["datArrayList"][data["datArrayList"].length-1][0], name:'小于1000'},
    		              {value:data["datArrayList"][data["datArrayList"].length-1][1], name:'1000-2000'},
    		              {value:data["datArrayList"][data["datArrayList"].length-1][2], name:'2000-3000'},
    		              {value:data["datArrayList"][data["datArrayList"].length-1][3], name:'3000-4000'},
    		              {value:data["datArrayList"][data["datArrayList"].length-1][4], name:'大于4000'}                    	  
                      ];
                      
                      show.setOption({
                    	  
                    	  series:[{
                    		  data:tmp
                    	  }]
                      })
                  }
              });  	  
        });
    })
    //点击上传压缩包
    $(function(){
        $("#filebtn2").click(function(){
        	//alert("上产文件");
        	//var filename=document.getElementById('fileField').value;
        	//document.getElementById('textfield').value='';
        	var filename=document.getElementById('textfield2').value;
        	var file = {"name":filename};
        	//alert(filename);
        	  $.ajax({
        		  async: true,
        		  beforeSend: function () {
              	    ShowDiv();
              	},
              	  complete: function () {
              	    HiddenDiv();
              	},
              	  
                  url: "readzip",
                  type:"POST",//请求方式POST
                  data:file,//传过去后台的json 这里注意前面加一个"json1="
                  dataType:"json", //预期从服务器中接受的数据类型
                  error:function(XMLHttpRequest, textStatus, errorThrown){
                      alert(XMLHttpRequest);
                      alert(textStatus);
                      alert(errorThrown);
                  },
                  success:function(data){
                      //alert(data.length);
                      //alert(data);
                      var tmp = [];
                      for(var i =0; i< data["datArrayList"].length;i++){
                      	temp = {
                      		symbol:"none",
                      		name:i,
                      	    type:"line",
                      	    data:data["datArrayList"][i]
                      	};
                      tmp.push(temp);
                      }
                      var option = {
		                      // 定义样式和数据
		                      backgroundColor: '#E5E5E5',//背景色
		                       title: {
		                       text: '用户96点用电数据',
		                       subtext :'时间段：'+data["startday"] +'-'+ data["endday"],
		                       subtextStyle:{
		                     	  color:'black'
		                       },
		                       left: 'center'
		                      },
		                      
		                      tooltip: {
		                          trigger: 'axis'
		                      },
		                      legend: {
		                          data: ['数据1']
		                      },
		               
		                      calculable: true,
		                      xAxis: [{
		                      	type: "category",
		                          axisLabel: {
		                          	 interval:4,
		                          	textStyle:{
		                  				color:'1a1a1a',  //坐标的字体颜色
		                  			}
		                          },
		                          axisLine: {
		                              lineStyle: {
		                                  color: '1a1a1a'
		                              }
		                          },
		                          data: ["00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"]
		                   
		                        }],
		                      yAxis: [{  
		                          type: 'value',
		                          axisLabel: {
		                          	textStyle:{
		                  				color:'1a1a1a',  //坐标的字体颜色
		                  			}
		                          },
		                          axisLine: {
		                              lineStyle: {
		                                  color: '1a1a1a'
		                              }
		                          }
		                      }],
		                      series:tmp
		                  };

		                  // 使用刚指定的配置项和数据显示图表。
		                  myChart3.setOption(option,true);
                    
                  }
              });  	  
        });
    })
    $(function(){
        $("#btn").click(function(){
        	//alert("聚类");
        	var filename=document.getElementById('textfield').value;
        	var file = {"name":filename,"data1":"2"};
        	
            cluster = $.ajax({
            	//async: false,
            	beforeSend: function () {
            	    ShowDiv();
            	},
            	complete: function () {
            	    HiddenDiv();
            	    //showAllMode();
            	},
                url: "show",
                type:"POST",//请求方式POST
                data:file,//传过去后台的json 这里注意前面加一个"json1="
                dataType:"json", //预期从服务器中接受的数据类型
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest);
                    alert(textStatus);
                    alert(errorThrown);
                },
                success:function(data){   
                	alert(data.length);
                    var tmp = [];
                    for(var i =0; i< data.length;i++){
                    	temp = {
                    	    symbol:"none",
                    		name:i,
                    	    type:"line",
                    	    data:data[i]
                    	};
                    tmp.push(temp);
                    }
                    var option = {
                            // 定义样式和数据
                             title: {
                             text: '当前用户负荷模式',
                             left: 'center'
                            },
                            backgroundColor: '#E5E5E5',
                            tooltip: {
                                trigger: 'axis'
                            },
                            legend: {
                                data: ['数据1']
                            },
                     
                            calculable: true,
                            xAxis: [{
                            	type: "category",
                                axisLabel: {
                                	 interval:1,
                                	 textStyle:{
                          				color:'1a1a1a',  //坐标的字体颜色
                          			}
                                },
                                axisLine: {
                                    lineStyle: {
                                        color: '#1a1a1a'
                                    }
                                },
                                data: ["00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"],
                              }],
                            yAxis: [{
                     
                                type: 'value',
                                axisLabel: {
                                 	textStyle:{
                         				color:'1a1a1a',  //坐标的字体颜色
                         			}
                                 },
                                 axisLine: {
                                     lineStyle: {
                                         color: '1a1a1a'
                                     }
                                 }
                            }] ,
                            series:tmp
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart2.setOption(option,true);
                 
                    showall();
                }
            });
        });
    })
  // 显示所有负荷数据
  $(function(){
	  $("#btn2").click(function(){
		  //alert(1);
		  var file = {"data1":"2"};
		      $.ajax({
		      	  async: true,	  
		          url: "GetAllMode",
		          type:"POST",//请求方式POST
		          data:file,//传过去后台的json 这里注意前面加一个"json1="
		          dataType:"json", //预期从服务器中接受的数据类型
		          error:function(XMLHttpRequest, textStatus, errorThrown){
		              alert(XMLHttpRequest);
		              alert(textStatus);
		              alert(errorThrown);
		          },
		          success:function(data){
		              var tmp = [];
		              for(var i =0; i< data.length;i++){
		              	temp = {
		              	    symbol:"none",
		              		name:i,
		              	    type:"line",
		              	    data:data[i]
		              	};
		              tmp.push(temp);
		              }
		              myChart.setOption({
		              	//series:[{name:'数据1',data:data[0]},{name:'数据2',data:data[1]},{name:'数据3',data:data[2]}]
		                  series:tmp
		              });
		              
		          }
		      });
	      });
	  })
    $(function(){
		  $("#btn4").click(function(){
			 // alert(1);
			  var file = {"data1":"2"};
			      $.ajax({
				    	 beforeSend: function () {
			            	    ShowDiv();
			              },
			             complete: function () {
			            	    HiddenDiv();
			            	    //showAllMode();
			              },
				      	  //async: true,	  
				          url: "fcm",
				          type:"POST",//请求方式POST
				          data:file,//传过去后台的json 这里注意前面加一个"json1="
				          dataType:"json", //预期从服务器中接受的数据类型
				          error:function(XMLHttpRequest, textStatus, errorThrown){
				              alert(XMLHttpRequest);
				              alert(textStatus);
				              alert(errorThrown);
				          },
				          success:function(data){			        	  
				        	  var j = data.length/2;	
				        	  var tmp = [];
				        	  /*
				              for(var i =0; i< 1;i++){
				              	temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
				              tmp.push(temp);
				              }
				              */
				        	  for(var i =0; i< 7;i++){
					              	temp = {
					              	    symbol:"none",
					              		name:i,
					              	    type:"line",
					              	    data:data[i]
					              	};
					              tmp.push(temp);
					              }
				              var tmp2 = [];
				              for(var i = 1; i< 2;i++){				            	 
				              	temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
				              tmp2.push(temp);
				              }
				              var tmp3 = [];
				              for(var i =2; i< 3;i++){
				              	temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
				              tmp3.push(temp);
				              }
				              var tmp4 = [];
				              for(var i =3; i< 4;i++){
				              	temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
				              tmp4.push(temp);
				              }
				              var tmp5 = [];
				              for(var i =4; i< 5;i++){
				              	temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
				              tmp5.push(temp);
				              }
				              var tmp6 = [];
				              for(var i =5; i< 6;i++){
				              	temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
				              tmp6.push(temp);
				              }
				              var tmp7 = [];
				              for(var i =6; i< 7;i++){
				              	temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
				              tmp7.push(temp);
				              }
				              myChart4.setOption({
					               series:tmp
					          });
				              myChart5.setOption({
				                  series:tmp2
				              });
				              myChart6.setOption({
					               series:tmp3
					          });
				              myChart7.setOption({
					               series:tmp4
					          });
				              myChart8.setOption({
					               series:tmp5
					          });
				              myChart9.setOption({
					               series:tmp6
					          });
				              myChart10.setOption({
					               series:tmp7
					          });
				              
				          }
				      });
		      });
		  })
	 $(function(){
		  $("#btn5").click(function(){
			 // alert(1);
			  var filename=document.getElementById('textfield').value;
			  var file = {"name":filename};
			      $.ajax({
				    	 beforeSend: function () {
			            	    ShowDiv();
			              },
			             complete: function () {
			            	    HiddenDiv();
			            	    //showAllMode();
			              },
				      	  //async: true,	  
				          url: "newuserclass",
				          type:"POST",//请求方式POST
				          data:file,//传过去后台的json 这里注意前面加一个"json1="
				          dataType:"json", //预期从服务器中接受的数据类型
				          error:function(XMLHttpRequest, textStatus, errorThrown){
				              alert(XMLHttpRequest);
				              alert(textStatus);
				              alert(errorThrown);
				          },
				          success:function(data){
				        	  //alert(data)
				        	  var tmp = [];				            
				        	  for(var i =0; i< data.length;i++){
				                temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
					            tmp.push(temp);
					          }
				             
				              myChart11.setOption({
				              	//series:[{name:'数据1',data:data[0]},{name:'数据2',data:data[1]},{name:'数据3',data:data[2]}]
				                  series:tmp
				              });
				          }
				      });
		      });
		  })
    $(function(){
        $("#btn6").click(function(){
        	//alert("保存数据");
        	var file = {"data1":"2"};     	
             $.ajax({
            	//async: false,
            	beforeSend: function () {
            	    ShowDiv();
            	},
            	complete: function () {
            	    HiddenDiv();
            	    //showAllMode();
            	},
                url: "savedata",
                type:"POST",//请求方式POST
                data:file,//传过去后台的json 这里注意前面加一个"json1="
                dataType:"json", //预期从服务器中接受的数据类型
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest);
                    alert(textStatus);
                    alert(errorThrown);
                },
                success:function(data){              	
                   alert("成功保存数据");
                   getinfo();
                }
            });
        });
    })
    function showall() {
    	//alert(1111);
    	var file = {"data1":"2"};
    	$.ajax({     	
	          url: "GetAllMode",
	          type:"POST",//请求方式POST
	          data:file,//传过去后台的json 这里注意前面加一个"json1="
	          dataType:"json", //预期从服务器中接受的数据类型
	          error:function(XMLHttpRequest, textStatus, errorThrown){
	              alert(XMLHttpRequest);
	              alert(textStatus);
	              alert(errorThrown);
	          },
	          success:function(data){      	  
	              var tmp = [];
	              for(var i =0; i< data.length;i++){
	              	temp = {
	              	    symbol:"none",
	              		name:i,
	              	    type:"line",
	              	    data:data[i]
	              	};
	              tmp.push(temp);
	              }
	              myChart.setOption({
	              	//series:[{name:'数据1',data:data[0]},{name:'数据2',data:data[1]},{name:'数据3',data:data[2]}]
	                  series:tmp
	              });
	          }
	      });
    }
   //获取已经添加数据
    function getinfo() {
    	//alert("获取信息");
    	var file = {"data1":"2"};
    	
    	$.ajax({     	
	          url: "Getinfo",
	          type:"POST",//请求方式POST
	          data:file,//传过去后台的json 这里注意前面加一个"json1="
	          dataType:"json", //预期从服务器中接受的数据类型
	          error:function(XMLHttpRequest, textStatus, errorThrown){
	              alert(XMLHttpRequest);
	              alert(textStatus);
	              alert(errorThrown);
	          },
	          success:function(data){      	  
	             //alert(data["startday"]);
	             //alert(data["endday"]);
	             //alert(data["daStrings"])
	             
	             document.getElementById("select").innerHTML = '';
	             var sel=document.getElementById("select");
	             sel.add(new Option("all","all"));
	             for(var i =0;i<data.length;i++){
	            	 var option = new Option(data[i],data[i]);
	 	         	 sel.add(option);
	             }
	         	 
	          }
	      });
    }
   //初始化数据库
   function initdata() {
    	//alert("初始化数据库");
    	var file = {"data1":"2"};	
    	$.ajax({     	
	          url: "initdataset",
	          type:"POST",//请求方式POST
	          data:file,//传过去后台的json 这里注意前面加一个"json1="
	          dataType:"json", //预期从服务器中接受的数据类型
	          error:function(XMLHttpRequest, textStatus, errorThrown){
	              alert(XMLHttpRequest);
	              alert(textStatus);
	              alert(errorThrown);
	          },
	          success:function(data){      	  
	            //alert(data);
	            getinfo();
	          }
	      });
    }
  //获取点击用户的信息
     $(function(){
		  $("#info").click(function(){
			  //alert("info");
			  var value = $("#select option:selected").val()
			  var file = {"name":value};
			  //alert(value);
			  $.ajax({
			    	 beforeSend: function () {
		            	    ShowDiv();
		              },
		             complete: function () {
		            	    HiddenDiv();
		            	    //showAllMode();
		              },
			      	  //async: true,	  
			          url: "ShowUserInfo",
			          type:"POST",//请求方式POST
			          data:file,//传过去后台的json 这里注意前面加一个"json1="
			          dataType:"json", //预期从服务器中接受的数据类型
			          error:function(XMLHttpRequest, textStatus, errorThrown){
			              alert(XMLHttpRequest);
			              alert(textStatus);
			              alert(errorThrown);
			          },
			          success:function(alldata){
			        	  
			        	 // alert(data)
			        	 data = alldata["datainfo"];
			        	 if(data == 2){
			        		 alert("选择具体用户");
			        	 }
			        	 else{
			        		 var tmp = [];				            
				        	  for(var i =0; i< data[0].length;i++){
				                temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[0][i]
				              	};
					            tmp.push(temp);
					          }
				             
				              myChart3.setOption({
				            	  title:{
		     	            			subtext :'时间段：'+alldata["startday"] +'-'+ alldata["endday"]
		     	            		 } ,
				              	//series:[{name:'数据1',data:data[0]},{name:'数据2',data:data[1]},{name:'数据3',data:data[2]}]
				                  series:tmp
				              });
				              var tmp = [];				            
				        	  for(var i =0; i< data[1].length-1;i++){
				                temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[1][i]
				              	};
					            tmp.push(temp);
					          }	             
				              myChart2.setOption({
				              	//series:[{name:'数据1',data:data[0]},{name:'数据2',data:data[1]},{name:'数据3',data:data[2]}]
				                  series:tmp
				              });
				              var tmp = [
		                    	  {value:data[1][data[1].length-1][0], name:'小于1000'},
		    		              {value:data[1][data[1].length-1][1], name:'1000-2000'},
		    		              {value:data[1][data[1].length-1][2], name:'2000-3000'},
		    		              {value:data[1][data[1].length-1][3], name:'3000-4000'},
		    		              {value:data[1][data[1].length-1][4], name:'大于4000'}                    	  
		                      ];
		                      show.setOption({
		                    	  series:[{
		                    		  data:tmp
		                    	  }]
		                      })
			        	 }
			          }
			      });
		      });
		  })
  //获取一段时间的用户信息
   $(function(){
		  $("#selectdays").click(function(){
			  //alert("info");
			  var start = document.getElementById("start").value;
			  var end = document.getElementById("end").value;
			  var value = $("#select option:selected").val()
			  var file = {"name":value,"start":start,"end":end};
			  
			 // alert(start);
			 // alert(end);
			  
			  $.ajax({
			    	 beforeSend: function () {
		            	    ShowDiv();
		              },
		             complete: function () {
		            	    HiddenDiv();
		            	    //showAllMode();
		              },
			      	  //async: true,	  
			          url: "getonedaydata",
			          type:"POST",//请求方式POST
			          data:file,//传过去后台的json 这里注意前面加一个"json1="
			          dataType:"json", //预期从服务器中接受的数据类型
			          error:function(XMLHttpRequest, textStatus, errorThrown){
			              alert(XMLHttpRequest);
			              alert(textStatus);
			              alert(errorThrown);
			          },
			          success:function(data){			        	  
			        	 //alert(data.length)			        	 
			        	 if(data == 2){
			        		 alert("选择具体用户");
			        	 }
			        	 else{
			        		 
			        		  var tmp = [];				            
				        	  for(var i =0; i< data.length-1;i++){
				                temp = {
				              	    symbol:"none",
				              		name:i,
				              	    type:"line",
				              	    data:data[i]
				              	};
					            tmp.push(temp);
					          }
				              var option = {
				                      // 定义样式和数据
				                      backgroundColor: '#E5E5E5',//背景色
				                       title: {
				                       text: '用户96点用电数据',
				                       subtext:'时间段：'+start +'-'+ end,
				                       subtextStyle:{
				                     	  color:'black'
				                       },
				                       left: 'center'
				                      },
				                      
				                      tooltip: {
				                          trigger: 'axis'
				                      },
				                      legend: {
				                          data: ['数据1']
				                      },
				               
				                      calculable: true,
				                      xAxis: [{
				                      	type: "category",
				                          axisLabel: {
				                          	 interval:4,
				                          	textStyle:{
				                  				color:'1a1a1a',  //坐标的字体颜色
				                  			}
				                          },
				                          axisLine: {
				                              lineStyle: {
				                                  color: '1a1a1a'
				                              }
				                          },
				                          data: ["00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"]
				                   
				                        }],
				                      yAxis: [{  
				                          type: 'value',
				                          axisLabel: {
				                          	textStyle:{
				                  				color:'1a1a1a',  //坐标的字体颜色
				                  			}
				                          },
				                          axisLine: {
				                              lineStyle: {
				                                  color: '1a1a1a'
				                              }
				                          }
				                      }],
				                      series:tmp
				                  };

				                  // 使用刚指定的配置项和数据显示图表。
				                  myChart3.setOption(option,true);
				              
				              var tmp = [
		                    	  {value:data[data.length-1][0], name:'小于1000'},
		    		              {value:data[data.length-1][1], name:'1000-2000'},
		    		              {value:data[data.length-1][2], name:'2000-3000'},
		    		              {value:data[data.length-1][3], name:'3000-4000'},
		    		              {value:data[data.length-1][4], name:'大于4000'}                    	  
		                      ];
		                      show.setOption({
		                    	  series:[{
		                    		  data:tmp
		                    	  }]
		                      })
			        	 }
			          }
			      });
			      
		      });
		  })
  //删除用户信息
   $(function(){
		  $("#delete").click(function(){
			  //alert("info");
			  var value = $("#select option:selected").val()
			  var file = {"name":value};
			  //document.getElementById("select").innerHTML = '';
			  //alert(value);
			  
			  $.ajax({
			    	 beforeSend: function () {
		            	    ShowDiv();
		              },
		             complete: function () {
		            	    HiddenDiv();
		            	    //showAllMode();
		              },
			      	  //async: true,	  
			          url: "deleteuserinfo",
			          type:"POST",//请求方式POST
			          data:file,//传过去后台的json 这里注意前面加一个"json1="
			          dataType:"json", //预期从服务器中接受的数据类型
			          error:function(XMLHttpRequest, textStatus, errorThrown){
			              alert(XMLHttpRequest);
			              alert(textStatus);
			              alert(errorThrown);
			          },
			          success:function(data){
			        	alert("删除成功！");
			        	getinfo();
			          }
			      });
			  
		      });
		  })
  //显示加载数据
    function ShowDiv() {
    $("#loading").show();
    }

    //隐藏加载数据
    function HiddenDiv() {
    $("#loading").hide();
    
    }   
  //  $(window).load(getinfo());
    $(window).load(initdata());
    window.onload = HiddenDiv;