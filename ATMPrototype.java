import java.util.HashMap;
import java.util.Scanner;

public class ATMPrototype {
	
	
		private HashMap<String,Double> hm=new HashMap<String,Double>();
		private int tray[]=new int[4];
		public void addUser(String name,Double amt)
		{
			if(hm.containsKey(name))
			{
				System.out.println("please use a different user name");
			}
			else
			{
				hm.put(name, amt);
			}
		}
		public void loadMoney(int a[])
		{
			
			for(int i=0;i<tray.length;i++)
				tray[i]+=a[i];
		}
		public void showBalance(String name)
		{
			if(hm.containsKey(name))
			{
				System.out.println(hm.get(name));
			}
			else
			{
				System.out.println("sorry you are not a registereed user of this ATM");
			}
		}
		public void showTrayStatus()
		{
			System.out.println("hundreds"+tray[0]+"twohundreds"+tray[1]+"fivehundreds"+tray[2]+"twoth"
					+ "ousands"+tray[3]);
		}

		public void withDrawMoney(String name,double amt)
		{
		 //to check if that user is registered as an user of the ATM
			if(hm.containsKey(name))
			{
				//checking if his account has sufficient balance
				if(amt>hm.get(name))
				{
					System.out.println("sorry" +name+"Requsted amount cannot be dispensed because"
							+ "of insufficient balance");
				}
				//if he has sufficient balance
				else
				{
					double ded=amt;
					//if the withdrawing amt is greater than 2000
					if(amt>=2000)
					{
						int cnt2000=0,cnt500=0,cnt200=0,cnt100=0;

						//System.out.println("hello");
						//if tray has ample number of notes to fulfill user request
						if(tray[3]>=(amt/2000))
						{
							//tray2000 has maximum number of notes and requested amt can be dispensed with just 2000notes
							if((Math.max(Math.max(tray[0], tray[1]),Math.max(tray[2],tray[3]))==tray[3])&&(amt%2000==0))
							{
								
								tray[3]-=amt/2000;
							}
							//if amount not satisfied
							else if(amt%2000!=0)
							{
								
					             while(amt>=2000)//making with 2000's
								{
									
									cnt2000+=(int)amt/2000;
									amt=amt%2000;
									break;
								}
								while(amt>=500)//making with 500's
								{
									
									cnt500+=(int)amt/500;
									amt=amt%500;
									break;
								}
								//if tray500 has maximum notes and amt satisfied
								if(Math.max((double)tray[2], Math.max(tray[0],tray[1]))==tray[2] && amt==0)
								{
									tray[3]-=cnt2000;
									tray[2]-=cnt500;
									
								}
								//if amt not satisfied
								else if(amt!=0)
								{
									while(amt>=200) //with 200's
									{
										
										cnt200+=(int)amt/200;
										amt=amt%200;
										break;
									}
									//if tray200 has maximum notes
									if(Math.max(tray[0],tray[1])==tray[1] && amt==0)
									{
										tray[3]-=cnt2000;
										tray[2]-=cnt500;
										tray[1]-=cnt200;
									}
									//amt not satisfied
									else if(amt!=0)
									{
										while(amt>=100)//doing with 100's
										{
											cnt100+=(int)amt/100;
											amt=amt%100;
											break;
										}
										if(amt==0)
										{
										tray[3]-=cnt2000;
										tray[2]-=cnt500;
										tray[1]-=cnt200;
										tray[0]-=cnt100;
										}
										else
										{
											System.out.println("sorry requested amount cant be dispensed");
										}
										
									}
									else//if tray200's not maximum
									{
										while(amt>=100)//doing with 100's
										{
											cnt100+=(int)amt/100;
											amt=amt%100;
											break;
										}
										if(amt==0)//if amount becomes zero
										{
											tray[3]-=cnt2000;
											tray[2]-=cnt500;
											tray[0]-=cnt100;
										}
										else//otherwise try with 200 notes
										{
											while(amt>=200) //with 200's
											{
												
												cnt200+=(int)amt/200;
												amt=amt%200;
												break;
											}
											if(amt==0)
											{
											tray[3]-=cnt2000;
											tray[2]-=cnt500;
											tray[1]-=cnt200;
											tray[0]-=cnt100;
											}
											else
											{
												System.out.println("requested amt cant be dispensed");
											}

										}
									}

								}
								
								else//if 500's are not maximum
								{
									//get the amount back and find which one is maximum
									double max=Math.max((double)tray[2], Math.max(tray[0],tray[1]));
									amt=amt+500*cnt500;
									boolean flag=false;
									while(amt>=max)
									{
										if(max==tray[0])
										{
											flag=true;
											cnt100+=(int)amt/100;
											amt=amt%100;
											break;
										}
										else
										{
											cnt200+=(int)amt/200;
											amt=amt%200;
											break;
										}
									}
								
									if(amt==0)
									{
										
										tray[3]-=cnt2000;
										if(flag==false)
										{
										tray[1]-=cnt200;
										
										}
										else
										tray[0]-=cnt100;
									}
									else
									{
										System.out.println("hello");
										if(flag==false)
										{
											
											while(amt>=100)
											{
												cnt100+=(int)amt/100;
												amt=amt%100;
												break;
											}
											tray[3]-=cnt2000;
											
											tray[1]-=cnt200;
											tray[0]-=cnt100;
										
										}
										else
										{
											while(amt>=200)
											{
												cnt200+=(int)amt/200;
												amt=amt%200;
												break;
											}
											tray[3]-=cnt2000;
											
											tray[1]-=cnt200;
											tray[0]-=cnt100;
										
										}
									}
									
									
									
									
								}

								
							}
							
							else//if 2000's are not maximum
							{
								double max=Math.max(Math.max(tray[0], tray[1]),Math.max(tray[2],tray[3]));
								amt=amt+2000*cnt2000;
								int flag=0;
								while(amt>=max)
								{
									if(max==tray[0])
									{
										flag=1;
										cnt100+=(int)amt/100;
										amt=amt%100;
										break;
									}
									else if(max==tray[1])
									{
										flag=2;
										cnt200+=(int)amt/200;
										amt=amt%200;
										break;
									}
									else
									{
										cnt500+=(int)amt/500;
										amt=amt%500;
										break;
									}
								}
							
								if(amt==0)
								{
									
									
									if(flag==2)
									{
									tray[1]-=cnt200;
									
									}
									else if(flag==1)
									tray[0]-=cnt100;
									else
									tray[2]-=cnt500;
								}
								else
								{
									System.out.println("hello");
									if(flag==0)
									{
										while(amt>=200)
										{
											cnt200+=(int)amt/200;
											amt=amt%200;
											break;
										}
										if(Math.max(tray[0],tray[1])==tray[1] && amt==0)
										{
											tray[2]-=cnt500;
											tray[1]-=cnt200;
										}
										else if(amt!=0)
										{
											while(amt>=100)
											{
												cnt100+=(int)amt/100;
												amt=amt%100;
												break;
											}
											if(amt==0)
											{
											tray[2]-=cnt500;
											tray[1]-=cnt200;
											tray[0]-=cnt100;
											}
											else
											{
												while(amt>=2000)
												{
													cnt2000+=(int)amt/2000;
													amt=amt%2000;
													break;
												}
												if(amt==0)
												{
													tray[2]-=cnt500;
													tray[0]-=cnt100;
													tray[1]-=cnt200;
                                                    tray[3]-=cnt2000;
												}
												else
												{
													System.out.println("sorry the requested amount cant be dispensed due to lack of denominations");
												}
											}
											
										}
										else//if 200s are not maximum
										{
											while(amt>=100)
											{
												cnt100+=(int)amt/100;
												amt=amt%100;
												break;
											}
											if(amt==0)
											{
												tray[2]-=cnt500;
												tray[0]-=cnt100;
											}
											else
											{
												while(amt>=200)
												{
													cnt200+=(int)amt/200;
													amt=amt%200;
													break;
												}
												if(amt==0)
												{
													tray[2]-=cnt500;
													tray[0]-=cnt100;
													tray[1]-=cnt200;
												}
												else
												{
													while(amt>=2000)
													{
														cnt2000+=(int)amt/2000;
														amt=amt%2000;
														break;
													}
													if(amt==0)
													{
														tray[2]-=cnt500;
														tray[0]-=cnt100;
														tray[1]-=cnt200;
                                                        tray[3]-=cnt2000;
													}
													else
													{
														System.out.println("sorry the requested amount cant be dispensed due to lack of denominations");
													}
												}

											}

										}
									
									}
									else if(flag==1)
									{
										while(amt>=200)
										{
											cnt200+=(int)amt/200;
											amt=amt%200;
											break;
										}
										if(Math.max(tray[1],tray[2])==tray[1] && amt==0)
										{
											tray[0]-=cnt100;
											tray[1]-=cnt200;
										}
										else if(amt!=0)
										{
											while(amt>=500)
											{
												cnt500+=(int)amt/500;
												amt=amt%500;
												break;
											}
											if(amt==0)
											{
											tray[2]-=cnt500;
											tray[1]-=cnt200;
											tray[0]-=cnt100;
											}
											else
											{
												while(amt>=2000)
												{
													cnt2000+=(int)amt/2000;
													amt=amt%2000;
													break;
												}
												if(amt==0)
												{
													tray[2]-=cnt500;
													tray[0]-=cnt100;
													tray[1]-=cnt200;
                                                    tray[3]-=cnt2000;
												}
												else
												{
													System.out.println("sorry the requested amount cant be dispensed due to lack of denominations");
												}
											}
											
										}
										else//if 200s are not maximum
										{
											while(amt>=500)
											{
												cnt500+=(int)amt/500;
												amt=amt%500;
												break;
											}
											if(amt==0)
											{
												tray[2]-=cnt500;
												tray[0]-=cnt100;
											}
											else
											{
												while(amt>=200)
												{
													cnt200+=(int)amt/200;
													amt=amt%200;
													break;
												}
												if(amt==0)
												{
													tray[2]-=cnt500;
													tray[0]-=cnt100;
													tray[1]-=cnt200;
												}
												else
												{
													while(amt>=2000)
													{
														cnt2000+=(int)amt/2000;
														amt=amt%2000;
														break;
													}
													if(amt==0)
													{
														tray[2]-=cnt500;
														tray[0]-=cnt100;
														tray[1]-=cnt200;
                                                        tray[3]-=cnt2000;
													}
													else
													{
														System.out.println("sorry the requested amount cant be dispensed due to lack of denominations");
													}
												}

											}

										}
									
									}
									
									else //if 200s are maximum flag==2
									{
										while(amt>=100)
										{
											cnt100+=(int)amt/100;
											amt=amt%100;
											break;
										}
										if(Math.max(tray[0],tray[2])==tray[0] && amt==0)
										{
											tray[0]-=cnt100;
											tray[1]-=cnt200;
										}
										else if(amt!=0)
										{
											while(amt>=500)
											{
												cnt500+=(int)amt/500;
												amt=amt%500;
												break;
											}
											if(amt==0)
											{
											tray[2]-=cnt500;
											tray[1]-=cnt200;
											tray[0]-=cnt100;
											}
											else
											{
												while(amt>=2000)
												{
													cnt2000+=(int)amt/2000;
													amt=amt%2000;
													break;
												}
												if(amt==0)
												{
													tray[2]-=cnt500;
													tray[0]-=cnt100;
													tray[1]-=cnt200;
                                                    tray[3]-=cnt2000;
												}
												else
												{
													System.out.println("sorry the requested amount cant be dispensed due to lack of denominations");
												}
											}
											
										}
										else
										{
											while(amt>=500)
											{
												cnt500+=(int)amt/500;
												amt=amt%500;
												break;
											}
											if(amt==0)
											{
												tray[2]-=cnt500;
												tray[1]-=cnt200;
											}
											else
											{
												while(amt>=100)
												{
													cnt100+=(int)amt/100;
													amt=amt%100;
													break;
												}
												if(amt==0)
												{
													tray[2]-=cnt500;
													tray[0]-=cnt100;
													tray[1]-=cnt200;
												}
												else
												{
													while(amt>=2000)
													{
														cnt2000+=(int)amt/2000;
														amt=amt%2000;
														break;
													}
													if(amt==0)
													{
														tray[2]-=cnt500;
														tray[0]-=cnt100;
														tray[1]-=cnt200;
                                                        tray[3]-=cnt2000;
													}
													else
													{
														System.out.println("sorry the requested amount cant be dispensed due to lack of denominations");
													}
												}

											}

										}
									
									}
									
									
								}
							}
						}
						hm.put(name,hm.get(name)-ded);
					}
					//if amount greater than 500 and less than 2000
					else if(amt>=500 && amt<2000)
						
					{
						int cnt500=0,cnt200=0,cnt100=0;
						System.out.println("hello");
						//if tray500 has sufficient number of notes
						if(tray[2]>=(amt/500))
						{
							//tray500 has maximum notes and amount becomes zero
							if(Math.max((double)tray[2], Math.max(tray[0],tray[1]))==tray[2]&&(amt%500==0))
							{
								
								tray[2]-=amt/500;
							}
							//if amount does not become zero
							else if(amt%500!=0)
							{
								
								
								while(amt>=500)
								{
									
									cnt500+=(int)amt/500;
									amt=amt%500;
									break;
								}
								while(amt>=200)
								{
									
									cnt200+=(int)amt/200;
									amt=amt%200;
									break;
								}
								if(Math.max(tray[0],tray[1])==tray[1] && amt==0)
								{
									tray[2]-=cnt500;
									tray[1]-=cnt200;
									
								}
								else if(amt!=0)
								{
									while(amt>=100)
									{
										
										cnt100+=(int)amt/100;
										amt=amt%100;
										break;
									}
									if(amt==0)
									{
									tray[2]-=cnt500;
									tray[1]-=cnt200;
									tray[0]-=cnt100;
									}
									else
									{
										System.out.println("sorry requested amount cant be dispensed");
									}
								}
								else
								{
									amt=amt+200*cnt200;
									while(amt>=100)
									{
										
										cnt100+=(int)amt/100;
										amt=amt%100;
										break;
									}
									if(amt==0)
									{
										tray[2]-=cnt500;
										tray[0]-=cnt100;
									}	
									else
									{
										while(amt>=200)
										{
											
											cnt200+=(int)amt/200;
											amt=amt%200;
											break;
										}
										if(amt==0)
										{
										tray[2]-=cnt500;
										tray[1]-=cnt200;
										tray[0]-=cnt100;
										}
										else
										{
											System.out.println("sorry requested amount cant be dispensed");
										}
									}	

									
								}
								
									

								}
							else//if 500s are not maximum
							{
								
								double max=Math.max((double)tray[2], Math.max(tray[0],tray[1]));
								amt=amt+500*cnt500;
								boolean flag=false;
								while(amt>=max)
								{
									if(max==tray[0])
									{
										flag=true;
										cnt100+=(int)amt/100;
										amt=amt%100;
										break;
									}
									else
									{
										cnt200+=(int)amt/200;
										amt=amt%200;
										break;
									}
								}
							
								if(amt==0)
								{
									
									//tray[2]-=cnt500;
									if(flag==false)
									{
									tray[1]-=cnt200;
									
									}
									else
									tray[0]-=cnt100;
								}
								else
								{
									System.out.println("hello");
									if(flag==false)
									{
										
										while(amt>=100)
										{
											cnt100+=(int)amt/100;
											amt=amt%100;
											break;
										}
										//tray[2]-=cnt500;
										if(amt==0)
										{
										tray[1]-=cnt200;
										tray[0]-=cnt100;
										}
										else
										{
											while(amt>=500)
											{
												cnt500+=(int)amt/500;
												amt=amt%500;
												break;
											}
											if(amt==0)
											{
											tray[1]-=cnt200;
											tray[0]-=cnt100;
											tray[2]-=cnt500;
											}
											else
											{
												System.out.println("requested amount cant be dispensed");
											}

										}
									
									}
									else
									{
										while(amt>=200)
										{
											cnt200+=(int)amt/200;
											amt=amt%200;
											break;
										}
										//tray[2]-=cnt500;
										
										if(amt==0)
										{
										tray[1]-=cnt200;
										tray[0]-=cnt100;
										}
										else
										{
											while(amt>=500)
											{
												cnt500+=(int)amt/500;
												amt=amt%500;
												break;
											}
											if(amt==0)
											{
											tray[1]-=cnt200;
											tray[0]-=cnt100;
											tray[2]-=cnt500;
											}
											else
											{
												System.out.println("requested amount cant be dispensed");
											}

										}
									
									}
									
									
								}
								
								
								
								
							}

							}
						hm.put(name,hm.get(name)-ded);
						}
					//if amount is greater than 200 and less than 500
              else if(amt>=200 && amt<500)
						
            {
	         int cnt200=0,cnt100=0;
	           System.out.println("hello");
	           //if tray200 has ample number of notes
	         if(tray[1]>=(amt/200))
	       {
	        	 //if tray200 has maximum notes and request fulfilled
		         if(Math.max(tray[0],tray[1])==tray[1]&&(amt%200)==0)
		       {
			
			      tray[1]-=amt/200;
		        }
		         //if amount didnot get satisfied
		      else if(amt%200!=0)
		       {
			
			
			   while(amt>=200)
			    {
				
				cnt200+=(int)amt/200;
				amt=amt%200;
				break;
			   }
			 while(amt>=100)
			 {
				
				cnt100+=(int)amt/100;
				amt=amt%100;
				break;
			 }
                if(amt==0)
                {
				tray[1]-=cnt200;
				tray[0]-=cnt100;
                }
                else
                {
                	System.out.println("sorry requested amount cant be dispensed");
                }
			
             }
		  else//if 200s are not maximum
		   {
			  amt=amt+200*cnt200;

			while(amt>=100)
			{
				
				cnt100+=(int)amt/100;
				amt=amt%100;
				break;
			}
                                            
             if(amt==0)
                                           
              {
                  tray[0]-=cnt100;

                }

		   else

           {
			while(amt>=200)
			{
				
				cnt200+=(int)amt/200;
				amt=amt%200;
				break;
			}
               if(amt==0)
               {
               tray[1]-=cnt200;
				tray[0]-=cnt100;
               }
               else
               {
            	   System.out.println("requested amount cant be dispensed");
               }
			}
			
		   }

		  }
	         hm.put(name,hm.get(name)-ded);
	       }
			//if amt greater than 100 and less than 200		
              else if(amt>=100 && amt<200)
              {
            	  int cnt100=0;
            	  //if tray has ample number of notes
            	  if(tray[0]>=amt/100)
            	  {
            		  while(amt>=100)
          			{
          				
          				cnt100+=(int)amt/100;
          				amt=amt%100;
          				break;
          			}
            		  if(amt==0)
            		  {
            			  tray[0]-=cnt100;
            			  hm.put(name,hm.get(name)-ded);
            		  }
            		  else
            		  {
            			  System.out.println("sorry your requested amt cannot be dispensed due to lack of denominations");
            			  
            		  }
            	  }
            	  
              }
              else
              {
    			  System.out.println("sorry your requested amt cannot be dispensed due to lack of denominations");

              }
					
		
				}
			}
				

			

			else
			{
				System.out.println("sorry, you are not a registered user of this ATM");
			}
					}
		
		public static void main(String args[])
		{
			ATMPrototype ap=new ATMPrototype();
			int a[]=new int[4];
			System.out.println("please enter the counts of each denomination you want to load into atm");
			Scanner sc=new Scanner(System.in);
			System.out.println("If you dont want to load a particular tray enter amount as 0 for that tray");
			for(int i=0;i<4;i++)
				a[i]=sc.nextInt();
			ap.loadMoney(a);
			ap.showTrayStatus();
			
			ap.addUser("neha",100000.0);
			ap.addUser("komal",20000.0);
			while(true) {
				System.out.println("please enter the amount to withdraw");
				double amt=sc.nextDouble();
				ap.withDrawMoney("neha",amt);
				ap.showBalance("neha");
				ap.showTrayStatus();
				System.out.println("do u want to continue or exit");
				System.out.println("type true if you want to stop otherwise false");
				boolean flag=sc.nextBoolean();
				if(flag==true)
					break;
			}
			System.out.println("Thank you for using our ATM");
			System.out.println("Have a nice day");
			
			
		}
		
	}
