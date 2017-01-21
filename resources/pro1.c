#include<conio.h>
#include<stdio.h>
#include<string.h>
void main()
{
	FILE *f;
	int i,l;
	char s[20];
	f=fopen("pro1out.txt","w");
	scanf("%d",&l);
	for(i=0;i<l;i++){
		scanf("%s",s);
		fprintf(f,"%s\n",s);
		printf("%s\n",s);	
	}
}
