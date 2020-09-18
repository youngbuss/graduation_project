var show = echarts.init(document.getElementById('show'));
     var option ={
    		 title: {
    	            text: '用电量分布图(单位：度)',
    	            // x 设置水平安放位置，默认左对齐，可选值：'center' ¦ 'left' ¦ 'right' ¦ {number}（x坐标，单位px）
    	            x: '20px',
    	            // y 设置垂直安放位置，默认全图顶端，可选值：'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
    	            y: 'top',
    	            // itemGap设置主副标题纵向间隔，单位px，默认为10，
    	            itemGap: 30,
    	            backgroundColor: '#EEE',
    	            // 主标题文本样式设置
    	            textStyle: {
    	              fontSize: 20,
    	              fontWeight: 'bolder',
    	              color: '#000080'
    	            }
    	          },
    	         legend: {
    	            // orient 设置布局方式，默认水平布局，可选值：'horizontal'（水平） ¦ 'vertical'（垂直）
    	            orient: 'vertical',
    	            // x 设置水平安放位置，默认全图居中，可选值：'center' ¦ 'left' ¦ 'right' ¦ {number}（x坐标，单位px）
    	            x: 'left',
    	            // y 设置垂直安放位置，默认全图顶端，可选值：'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
    	            y: '25px',
    	            itemWidth: 24,   // 设置图例图形的宽
    	            itemHeight: 18,  // 设置图例图形的高
    	            textStyle: {
    	              color: '#666'  // 图例文字颜色
    	            },
    	            // itemGap设置各个item之间的间隔，单位px，默认为10，横向布局时为水平间隔，纵向布局时为纵向间隔
    	            itemGap: 30,
    	            backgroundColor: '#eee',  // 设置整个图例区域背景颜色
    	            data: ['小于1000','1000-2000','2000-3000','3000-4000','大于4000']
    	          },
    		series: [
    		        {
    		          name: '用电量',
    		          type: 'pie',
    		           radius: '70%',  // 设置饼状图大小，100%时，最大直径=整个图形的min(宽，高)
    		          //radius: ['30%', '60%'],  // 设置环形饼状图， 第一个百分数设置内圈大小，第二个百分数设置外圈大小
    		          center: ['60%', '60%'],  // 设置饼状图位置，第一个百分数调水平位置，第二个百分数调垂直位置
    		          data: [
    		              {value:0, name:'小于1000'},
    		              {value:0, name:'1000-2000'},
    		              {value:0, name:'2000-3000'},
    		              {value:0, name:'3000-4000'},
    		              {value:0, name:'大于4000'}
    		          ],
    		          // itemStyle 设置饼状图扇形区域样式
    		          itemStyle: {
    		            // emphasis：英文意思是 强调;着重;（轮廓、图形等的）鲜明;突出，重读
    		            // emphasis：设置鼠标放到哪一块扇形上面的时候，扇形样式、阴影
    		           
    		            normal:{ 
					           label:{ 
					              show: true, 
					              formatter: '{b} : {c} ({d}%)' 
					              }, 
					              labelLine :{show:true} 
					              } 				         
    		          }
    
    		        }
    		      ],
    		tooltip: {
    		        // trigger 设置触发类型，默认数据触发，可选值：'item' ¦ 'axis'
    		        trigger: 'item',
    		        showDelay: 20,   // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
    		        hideDelay: 20,   // 隐藏延迟，单位ms
    		        backgroundColor: 'rgba(255,0,0,0.7)',  // 提示框背景颜色
    		        textStyle: {
    		          fontSize: '16px',
    		          color: '#000'  // 设置文本颜色 默认#FFF
    		        },
    		        // formatter设置提示框显示内容
    		        // {a}指series.name  {b}指series.data的name
    		        // {c}指series.data的value  {d}%指这一部分占总数的百分比
    		        formatter: '{a} <br/>{b} : {c}个 ({d}%)'
    		      }
    		}
     show.setOption(option);
     var myChart3 = echarts.init(document.getElementById('main3'));
     var option = {
             // 定义样式和数据
             backgroundColor: '#E5E5E5',//背景色
              title: {
              text: '用户96点用电数据',
              subtext:'',
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
             }]
         };

         // 使用刚指定的配置项和数据显示图表。
         myChart3.setOption(option);
    var myChart2 = echarts.init(document.getElementById('main2'));
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
            }] 
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option);
        var myChart = echarts.init(document.getElementById('main1'));
        var option = {
                // 定义样式和数据
                 title: {
                 text: '所有用户负荷模式',
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
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            var myChart5 = echarts.init(document.getElementById('main5'));
            var option = {
                    // 定义样式和数据
                     title: {
                     text: '用户分组类别',
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
                    }] 
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart5.setOption(option);
                var myChart4 = echarts.init(document.getElementById('main4'));
                var option = {
                        // 定义样式和数据
                         title: {
                         text: '用户分组类别',
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
                        }] 
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart4.setOption(option);
                    var myChart6 = echarts.init(document.getElementById('main6'));
                
                    // 使用刚指定的配置项和数据显示图表。
                    myChart6.setOption(option);
                    var myChart7 = echarts.init(document.getElementById('main7'));
                    var myChart8 = echarts.init(document.getElementById('main8'));
                    var myChart9 = echarts.init(document.getElementById('main9'));
                    var myChart10 = echarts.init(document.getElementById('main10'));
                    myChart7.setOption(option);
                    myChart8.setOption(option);
                    myChart9.setOption(option);
                    myChart10.setOption(option);
                    var myChart11 = echarts.init(document.getElementById('main11'));
                    var option = {
                            // 定义样式和数据
                             title: {
                             text: '新用户分组',
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
                            }] 
                        };
                    myChart11.setOption(option);
