#include<stdio.h>
#include<stdlib.h>
#include<string.h>
struct everybody{ /*structýmýz*/
		char type[20];
		char name[20];
		int hp;
		int ad;
		int x;
		int y;
		int xp;	
	};
int satir_sayisi(FILE *myfile)	{ /*satir sayisi alma */
	char line [100],*string;
	int satir_satir = 0;
	while (fgets(line, 100,myfile) != NULL){
		satir_satir++;
	}
	rewind(myfile);
	return satir_satir;
}
int main(int argc, char const *argv[]){
	FILE * file = fopen(argv[1],"r");
	FILE * output = fopen(argv[3],"w");
	
	struct everybody *all = (struct everybody*)malloc(sizeof(struct everybody)*satir_sayisi(file)); /* deðiþkenler*/
	char line[999];
	char **arr;	
	int k,doner,doner2,sayac=0,sayac2=0,sayici=0,structmember=0;		
	char *abi[4];
	char *token;
	while (fgets(line, 999, file) != NULL)  { /*teker teker satýrlarý alýyoruz*/
    	token = strtok(line, ",");
   		structmember++;
   int j=0;
   while( token != NULL ) { /*satýrlarý ,lerden parcalýyoruz*/
    	abi[j]=token; /*elemanlarý listeye atýyoruz*/
    	j++;
      token = strtok(NULL, ",");
   }
   all[k].ad=atoi(abi[3]);  /* structýmýzýn içine atýyoruz aldýgýmýz verileri */
   all[k].hp=atoi(abi[2]);
   strcpy(all[k].name,abi[1]);
   strcpy(all[k].type,abi[0]);
   all[k].xp=0;
   k++;
}	
	FILE *file2 = fopen(argv[2],"r"); /* command txtsini alýyoruz*/
	char line2[999];
	char *hepsi[999];
	char * token2;
	int i=0,j=0,xad,yad,u;   /* döngü elemanlarýmýz , arraylerimiz*/
	int temp1,temp2; /* döngü*/ 
	int xval,yval; /* harita için*/
	while (fgets(line2, 999, file2) != NULL)  { /*commandsleri alýyoruz satýr olarak*/
		int spacec=1,y; /* bir satýrda kaç kelime oldugunu görmek için */
		for(y=0;y<strlen(line2);y++){
			if (line2[y]== ' '){
				
				spacec++;
			}
		}
	sayici++;   
    token2 = strtok(line2, " "); /* command linedaki 2.kelimeyi alýyoruz  */ 	
   	if (strcmp(token2,"PUT")==0){ /* put ise*/
   		token2 = strtok(NULL," ");
		if (strcmp(token2,"HERO")==0){/* hero durumu ile monster durumu */
			spacec=(spacec-2)/3;
			for(u=0;u<spacec;u++){
			token2 = strtok(NULL, " ");
			for(i=0;i<k;++i){
				if(strcmp(all[i].name,token2)==0){ /*structýmýza atýyoruz degerlerimizi*/
					token2=strtok(NULL, " ");
					all[i].x=atoi(token2);
					token2=strtok(NULL, " ");
					all[i].y=atoi(token2);
					arr[all[i].x][all[i].y]=all[i].name[0];
				}
			}
			}
		}
		if (strcmp(token2,"MONSTER")==0){ /*monster ise */ /**/
			spacec=(spacec-2)/3;
			for(u=0;u<spacec;u++){
				token2 = strtok(NULL, " ");
				for(i=0;i<k;++i){
					if(strcmp(all[i].name,token2)==0){
						token2=strtok(NULL, " ");
						all[i].x=atoi(token2);
						token2=strtok(NULL, " ");
						all[i].y=atoi(token2);
						arr[all[i].x][all[i].y]=all[i].name[0];
				}
			}
			}
		}
	   } /* put hero kýsým son */
	if(strcmp(token2,"LOADMAP")==0){ /* dinamik 2d arrayimizi yüklüyoruz*/
		token2 =strtok(NULL," ");
		xval=atoi(token2);
		token2=strtok(NULL," ");
		yval=atoi(token2);
		int count = 0,i,j;
		arr = (char **)malloc(sizeof(char *) * xval);
		arr[0] = (char *)malloc(sizeof(char) * yval * xval);
		for(i = 0; i < xval; i++){
			arr[i] = (*arr + yval * i);}
		for (i = 0; i < xval; i++){
			for (j = 0; j < yval; j++){
			arr[i][j] = '.';
			 }
	}
} /* loadmap kýsmýnýn sonu*/
	int alivem=0,aliveh=0,hps,hps2; /*oyun bitiþ kýsmýný inceliyoruz */
	for(hps=0;hps<k;hps++){
		if(strncmp(all[hps].type,"MONSTER",7)==0){
			if(all[hps].hp!=0){
				alivem++;
			}
		}
		if(strncmp(all[hps].type,"HERO",4)==0){
			if(all[hps].hp!=0){
				aliveh++;
			}
		}
	}
	if (alivem==0){
		fprintf(output,"ALL MONSTERS ARE DEFEATED\n");
		break;
	}
	if(aliveh==0){
		fprintf(output,"ALL HEROES ARE DEFEATED\n");
		break;
	}
	if(strcmp(token2,"SHOW")==0){ /* show kýsmý ile 3 komut var ucunu de tek loopda bakabiliriz*/
		token2 =strtok(NULL," ");
		if(strncmp(token2,"MAP",3)==0){
			fprintf(output,"MAP STATUS\n");
		for(temp1=0;temp1<xval;temp1++){
			
			for(temp2=0;temp2<yval;temp2++){
				fprintf(output,"%c",arr[temp1][temp2]);
			}
			fprintf(output,"\n");
			
		}
		fprintf(output,"\n");			
		}
		if((strncmp(token2,"HERO",4)==0)){ /* show hero gelirse*/
			fprintf(output,"HERO STATUS\n");
			for(sayac=0;sayac<k;sayac++){
				if(strncmp(all[sayac].type,"HERO",4)==0){
					fprintf(output,"%s HP:%d XP:%d\n",all[sayac].name,all[sayac].hp,all[sayac].xp);					
				}
				if(sayac==k-1){
					fprintf(output,"\n");
				}
			}
			
			
		}
		if((strncmp(token2,"MONSTER",7)==0)){ /*show monster gelirse*/
			fprintf(output,"MONSTER STATUS\n");
			for(sayac2=0;sayac2<k;sayac2++){
				if(strncmp(all[sayac2].type,"MONSTER",7)==0){
					fprintf(output,"%s HP:%d \n",all[sayac2].name,all[sayac2].hp);			
				}
				if(sayac2==k-1){
					fprintf(output,"\n");
				}
			}
		/*	fprintf(output,"\n");	*/
		}
	} /* show kýsmý*/
	int tem1,tem2; /*move kýsmýna bakýyoruz*/
	if(strncmp(token2,"MOVE",4)==0){
		token2=strtok(NULL, " ");
		if(strncmp(token2,"HERO",4)==0){ /* sadece herolar hareket edebilir */
			spacec=(spacec-2)/3;
			fprintf(output,"HEROES MOVED\n");
			for(doner=0;doner<spacec;doner++){
				token2 = strtok(NULL, " ");
				for(i=0;i<k;++i){
					if(strncmp(all[i].name,token2,5)==0){
						if(all[i].hp==0){
							fprintf(output,"%s can't move. Dead.\n",all[i].name);
						}
						token2=strtok(NULL, " ");
						tem1=atoi(token2);												
						token2=strtok(NULL, " ");
						tem2=atoi(token2);
						if(tem1>=xval || tem2 >=yval){
							fprintf(output,"%s can't move. There is a wall.\n",all[i].name);
						}
						else if(arr[tem1][tem2]=='.'){		
							arr[tem1][tem2]=(all[i].name)[0];
							arr[all[i].x][all[i].y]='.';
							all[i].x=tem1;
							all[i].y=tem2;					
						}
						else if(arr[tem1][tem2]!='.'){
							fprintf(output,"%s can’t move. Place is occupied.\n",all[i].name);
						}	
					}					
			}
		}
	}
	fprintf(output,"\n");	
} /*move hero -1,0;-1,+1;0,+1;+1,+1;+1,0;+1,-1;0,-1,;1,-1;olasý saldýrý poziyonlarý*/
	
	int tem5,tem6,tem7,tem8,tem9,tem10,tem11,tem12,tem13; /* attack moduna bakýyoruz monster ve hero için ayrý ayrý*/
	if(strncmp(token2,"ATTACK",6)==0){
		
		token2=strtok(NULL, " ");
		if (strncmp(token2,"MONSTER",7)==0){
			fprintf(output,"MONSTERS ATTACKED\n\n");
			for(tem5=0;tem5<k;tem5++){
				if(strncmp(all[tem5].type,"MONSTER",7)==0){  /* monsterlarýn saldýrma zamaný*/
					if(all[tem5].hp==0){
						continue;
					}
					if((all[tem5].x>=0 && all[tem5].x<xval)&&(all[tem5].y+1>=0 && all[tem5].y+1<yval)){
						if(arr[all[tem5].x][all[tem5].y+1]!='.'){
							for(tem6=0;tem6<k;tem6++){
								if(arr[all[tem5].x][all[tem5].y+1]==all[tem6].name[0]){
									if(strncmp(all[tem6].type,"MONSTER",7)==0){          /* monster ise vurmuyoruz*/
										continue;
									}
									all[tem6].hp=all[tem6].hp-all[tem5].ad;
									if(all[tem6].hp<=0){
										all[tem6].hp=0;
										all[tem5].xp++;
										arr[all[tem6].x][all[tem6].y]='.';}
								}
							}
						}
					}
					if((all[tem5].x+1>=0 && all[tem5].x+1<xval)&&(all[tem5].y+1>=0 && all[tem5].y+1<yval)){
						if(arr[all[tem5].x+1][all[tem5].y+1]!='.'){
							for(tem7=0;tem7<k;tem7++){
								if(arr[all[tem5].x+1][all[tem5].y+1]==all[tem7].name[0]){
									if(strncmp(all[tem7].type,"MONSTER",7)==0){
										continue;
									}
									all[tem7].hp=all[tem7].hp-all[tem5].ad;
									if(all[tem7].hp<=0){
										all[tem7].hp=0;
										all[tem5].xp++;
										arr[all[tem7].x][all[tem7].y]='.';}
								}
							}
						}
					}
					if((all[tem5].x+1>=0 && all[tem5].x+1<xval)&&(all[tem5].y>=0 && all[tem5].y<yval)){
						if(arr[all[tem5].x+1][all[tem5].y]!='.'){
							for(tem8=0;tem8<k;tem8++){
								if(arr[all[tem5].x+1][all[tem5].y]==all[tem8].name[0]){
									if(strncmp(all[tem8].type,"MONSTER",7)==0){
										continue;
									}
									all[tem8].hp=all[tem8].hp-all[tem5].ad;
									
									if(all[tem8].hp<=0){
										all[tem8].hp=0;
										all[tem5].xp++;
										arr[all[tem8].x][all[tem8].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ*/
					if((all[tem5].x+1>=0 && all[tem5].x+1<xval)&&(all[tem5].y-1>=0 && all[tem5].y-1<yval)){
						if(arr[all[tem5].x+1][all[tem5].y-1]!='.'){
							for(tem9=0;tem9<k;tem9++){
								if(arr[all[tem5].x+1][all[tem5].y-1]==all[tem9].name[0]){
									if(strncmp(all[tem9].type,"MONSTER",7)==0){
										continue;
									}
									all[tem9].hp=all[tem9].hp-all[tem5].ad;
									
									if(all[tem9].hp<=0){
										all[tem9].hp=0;
										all[tem5].xp++;
										arr[all[tem9].x][all[tem9].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[tem5].x>=0 && all[tem5].x<xval)&&(all[tem5].y-1>=0 && all[tem5].y-1<yval)){
						if(arr[all[tem5].x][all[tem5].y-1]!='.'){
							for(tem10=0;tem10<k;tem10++){
								if(arr[all[tem5].x][all[tem5].y-1]==all[tem10].name[0]){
									if(strncmp(all[tem10].type,"MONSTER",7)==0){
										continue;
									}
									all[tem10].hp=all[tem10].hp-all[tem5].ad;
									if(all[tem10].hp<=0){
										all[tem10].hp=0;
										all[tem5].xp++;
										arr[all[tem10].x][all[tem10].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[tem5].x-1>=0 && all[tem5].x-1<xval)&&(all[tem5].y-1>=0 && all[tem5].y-1<yval)){
						if(arr[all[tem5].x-1][all[tem5].y-1]!='.'){
							for(tem11=0;tem11<k;tem11++){
								if(arr[all[tem5].x-1][all[tem5].y-1]==all[tem11].name[0]){
									if(strncmp(all[tem11].type,"MONSTER",7)==0){
										continue;
									}
									all[tem11].hp=all[tem11].hp-all[tem5].ad;
									if(all[tem11].hp<=0){
										all[tem11].hp=0;
										all[tem5].xp++;
										arr[all[tem11].x][all[tem11].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[tem5].x-1>=0 && all[tem5].x-1<xval)&&(all[tem5].y>=0 && all[tem5].y<yval)){
						if(arr[all[tem5].x-1][all[tem5].y]!='.'){
							for(tem12=0;tem12<k;tem12++){
								if(arr[all[tem5].x-1][all[tem5].y]==all[tem12].name[0]){
									if(strncmp(all[tem12].type,"MONSTER",7)==0){
										continue;
									}
									all[tem12].hp=all[tem12].hp-all[tem5].ad;
									if(all[tem12].hp<=0){
										all[tem12].hp=0;
										all[tem5].xp++;
										arr[all[tem12].x][all[tem12].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[tem5].x-1>=0 && all[tem5].x-1<xval)&&(all[tem5].y+1>=0 && all[tem5].y+1<yval)){
						if(arr[all[tem5].x-1][all[tem5].y+1]!='.'){
							for(tem13=0;tem13<k;tem13++){
								if(arr[all[tem5].x-1][all[tem5].y+1]==all[tem13].name[0]){
									if(strncmp(all[tem13].type,"MONSTER",7)==0){
										continue;
									}
									all[tem13].hp=all[tem13].hp-all[tem5].ad;
									if(all[tem13].hp<=0){
										all[tem13].hp=0;
										all[tem5].xp++;
										arr[all[tem13].x][all[tem13].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ	*/
				}
			}
		}/* monster sýrasý bitiþ*/
		int temp5,temp6,temp7,temp8,temp9,temp10,temp11,temp12,temp13,alive2; /* herolarýn saldýrma zamaný*/
		if (strncmp(token2,"HERO",4)==0){
			fprintf(output,"HEROES ATTACKED\n\n");
			for(temp5=0;temp5<k;temp5++){
				if(strncmp(all[temp5].type,"HERO",4)==0){
					if(all[temp5].hp==0){
						continue;
					}
					if((all[temp5].x>=0 && all[temp5].x<xval)&&(all[temp5].y+1>=0 && all[temp5].y+1<yval)){
						if(arr[all[temp5].x][all[temp5].y+1]!='.'){
							for(temp6=0;temp6<k;temp6++){
								if(arr[all[temp5].x][all[temp5].y+1]==all[temp6].name[0]){
									if(strncmp(all[temp6].type,"HERO",4)==0){         /* hero ise vurmuyoruz*/
										continue;
									}
									all[temp6].hp=all[temp6].hp-all[temp5].ad;
									if(all[temp6].hp<=0){                /* hp  0 ve aþagýsý olursa oluyor xp veriyor ve siliyor*/
										all[temp6].hp=0;
										all[temp5].xp++;
										arr[all[temp6].x][all[temp6].y]='.';}
								}
							}
						}
					}
					if((all[temp5].x+1>=0 && all[temp5].x+1<xval)&&(all[temp5].y+1>=0 && all[temp5].y+1<yval)){
						if(arr[all[temp5].x+1][all[temp5].y+1]!='.'){
							for(temp7=0;temp7<k;temp7++){
								if(arr[all[temp5].x+1][all[temp5].y+1]==all[temp7].name[0]){
									if(strncmp(all[temp7].type,"HERO",4)==0){
										continue;
									}
									all[temp7].hp=all[temp7].hp-all[temp5].ad;
									
									if(all[temp7].hp<=0){
										all[temp7].hp=0;
										all[temp5].xp++;
										arr[all[temp7].x][all[temp7].y]='.';}
								}
							}
						}
					}
					if((all[temp5].x+1>=0 && all[temp5].x+1<xval)&&(all[temp5].y>=0 && all[temp5].y<yval)){
						if(arr[all[temp5].x+1][all[temp5].y]!='.'){
							for(temp8=0;temp8<k;temp8++){
								if(arr[all[temp5].x+1][all[temp5].y]==all[temp8].name[0]){
									if(strncmp(all[temp8].type,"HERO",4)==0){
										continue;
									}
									all[temp8].hp=all[temp8].hp-all[temp5].ad;	
									if(all[temp8].hp<=0){
										all[temp8].hp=0;
										all[temp5].xp++;
										arr[all[temp8].x][all[temp8].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[temp5].x+1>=0 && all[temp5].x+1<xval)&&(all[temp5].y-1>=0 && all[temp5].y-1<yval)){
						if(arr[all[temp5].x+1][all[temp5].y-1]!='.'){
							for(temp9=0;temp9<k;temp9++){
								if(arr[all[temp5].x+1][all[temp5].y-1]==all[temp9].name[0]){
									if(strncmp(all[temp9].type,"HERO",4)==0){
										continue;
									}
									all[temp9].hp=all[temp9].hp-all[temp5].ad;
									if(all[temp9].hp<=0){
										all[temp9].hp=0;
										all[temp5].xp++;
										arr[all[temp9].x][all[temp9].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[temp5].x>=0 && all[temp5].x<xval)&&(all[temp5].y-1>=0 && all[temp5].y-1<yval)){
						if(arr[all[temp5].x][all[temp5].y-1]!='.'){
							for(temp10=0;temp10<k;temp10++){
								if(arr[all[temp5].x][all[temp5].y-1]==all[temp10].name[0]){
									if(strncmp(all[temp10].type,"HERO",4)==0){
										continue;
									}
									all[temp10].hp=all[temp10].hp-all[temp5].ad;
									if(all[temp10].hp<=0){
										all[temp10].hp=0;
										all[temp5].xp++;
										arr[all[temp10].x][all[temp10].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[temp5].x-1>=0 && all[temp5].x-1<xval)&&(all[temp5].y-1>=0 && all[temp5].y-1<yval)){
						if(arr[all[temp5].x-1][all[temp5].y-1]!='.'){
							for(temp11=0;temp11<k;temp11++){
								if(arr[all[temp5].x-1][all[temp5].y-1]==all[temp11].name[0]){
									if(strncmp(all[temp11].type,"HERO",4)==0){
										continue;
									}
									all[temp11].hp=all[temp11].hp-all[temp5].ad;
									if(all[temp11].hp<=0){
										all[temp11].hp=0;
										all[temp5].xp++;
										arr[all[temp11].x][all[temp11].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[temp5].x-1>=0 && all[temp5].x-1<xval)&&(all[temp5].y>=0 && all[temp5].y<yval)){
						if(arr[all[temp5].x-1][all[temp5].y]!='.'){
							for(temp12=0;temp12<k;temp12++){
								if(arr[all[temp5].x-1][all[temp5].y]==all[temp12].name[0]){
									if(strncmp(all[temp12].type,"HERO",4)==0){
										continue;
									}
									all[temp12].hp=all[temp12].hp-all[temp5].ad;	
									if(all[temp12].hp<=0){
										all[temp12].hp=0;
										all[temp5].xp++;
										arr[all[temp12].x][all[temp12].y]='.';}
								}
							}
						}
					} /* kordinat bitiþ */
					if((all[temp5].x-1>=0 && all[temp5].x-1<xval)&&(all[temp5].y+1>=0 && all[temp5].y+1<yval)){
						if(arr[all[temp5].x-1][all[temp5].y+1]!='.'){
							for(temp13=0;temp13<k;temp13++){
								if(arr[all[temp5].x-1][all[temp5].y+1]==all[temp13].name[0]){
									if(strncmp(all[temp13].type,"HERO",4)==0){
										continue;
									}
									all[temp13].hp=all[temp13].hp-all[temp5].ad;
									if(all[temp13].hp<=0){
										all[temp13].hp=0;
										all[temp5].xp++;
										arr[all[temp13].x][all[temp13].y]='.';}
								}
							}
						}
					} /* koordinat bitiþ*/
				}	
			}			
		}/* monster sýrasý bitiþ*/
	}
	 /* atttack */	
} /* while son */
	free(all);	/* malloc pointerýmýzý býrakýyoruz */
	fclose(file);
	fclose(file2);
	fclose(output);
	return 0;
}
