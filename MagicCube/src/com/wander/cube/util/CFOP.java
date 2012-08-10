package com.wander.cube.util;

/**
 * Solve Rubik's Cube By CFOP
 * @author ChenWu http://www.diy-robots.com
 * @see CFOP
 */
public class CFOP
{
	static String SideColors[] = {
		"orgorwwoo",
		"oyggbobrg",
		"yyrgowwbw",
		"yrybgybbo",
		"gwwyybror",
		"bgrwwrbgy"
	};

	public static void main (String[] aArg) throws Exception
	{
		Steps.Clear();
		if(Rubik.ImportColor(SideColors)=="")
		{
			TopCross();
			TopCorner();
			SecondLayer();
			BottomCross();
			BottomCorner();
			ThirdLayerCorner();
			ThirdLayerCornerSnap();
			ThirdLayerBorderSnap();
		}
		//Now the steps are saved in Steps Object
		for(int i=0;i<Steps.Count;i++)
		{
			Steps.Index=i;
			//int c = Steps.Color(); //Color
			//bool ColockWise = Steps.ClockWise(); //Rotate direction
			//int Quarter = Steps.Quarter(); //Number of quarter be rotated
		}
	}
	
	public static void TopCross() throws Exception
	{
		int GetPos=0;
		int[] c=new int[5];
		int CurrentStep=Steps.Count;
		
		c[0]=Rubik.Sides[0][1][1];
		c[1]=Rubik.Sides[1][1][1];
		c[2]=Rubik.Sides[5][1][1];
		c[3]=Rubik.Sides[3][1][1];
		c[4]=Rubik.Sides[4][1][1];
		for(int i=1;i<5;i++)
		{
			CurrentStep=Steps.Count;
			Rubik.ChangeViewToColor(c[0]);
			Rubik.ChangeViewFromTopByNextColor(c[i]);
			GetPos=Rubik.FindBorderCell(c[0],c[i]);

			switch(GetPos)
			{
			case 101:
				//Already fixed
				break;
			case 110:
				Steps.AddStep(1, false, 1);
				Steps.AddStep(0, true, 1);
				Steps.AddStep(4, false, 1);
				Steps.AddStep(0, false, 1);
				break;				
			case 140:
				Steps.AddStep(4, true, 1);
				Steps.AddStep(1, true, 1);
				break;
			case 104:
				Steps.AddStep(4, true, 1);
				Steps.AddStep(0, true, 1);
				Steps.AddStep(4, false, 1);
				Steps.AddStep(0, false, 1);
				break;
			case 130:
				Steps.AddStep(3, true, 1);
				Steps.AddStep(0, true, 1);
				Steps.AddStep(4, true, 1);
				Steps.AddStep(0, false, 1);
				break;
			case 103:
				Steps.AddStep(3, true, 1);
				Steps.AddStep(0, true, 2);
				Steps.AddStep(3, false, 1);
				Steps.AddStep(0, false, 2);
				break;
			case 150:
				Steps.AddStep(5, false, 1);
				Steps.AddStep(1, false, 1);
				break;
			case 105:
				Steps.AddStep(5, false, 1);
				Steps.AddStep(0, false, 1);
				Steps.AddStep(5, true, 1);
				Steps.AddStep(0, true, 1);
				break;
			case 241:
				Steps.AddStep(1, true, 1);
				break;
			case 214:
				Steps.AddStep(0, true, 1);
				Steps.AddStep(4, false, 1);
				Steps.AddStep(0, false, 1);
				break;
			case 243:
				Steps.AddStep(4, true, 2);
				Steps.AddStep(1, true, 1);
				Steps.AddStep(4, true, 2);
				break;
			case 234:
				Steps.AddStep(0, true, 1);
				Steps.AddStep(4, true, 1);
				Steps.AddStep(0, false, 1);
				break;
			case 253:
				Steps.AddStep(5, true, 2);
				Steps.AddStep(1, false, 1);
				Steps.AddStep(5, true, 2);
				break;
			case 235:
				Steps.AddStep(0, false, 1);
				Steps.AddStep(5, false, 1);
				Steps.AddStep(0, true, 1);
				break;
			case 251:
				Steps.AddStep(1, false, 1);
				break;
			case 215:
				Steps.AddStep(0, false, 1);
				Steps.AddStep(5, true, 1);
				Steps.AddStep(0, true, 1);
				break;
			case 312:
				Steps.AddStep(2,true,1);
				Steps.AddStep(5,true,1);
				Steps.AddStep(1,false,1);
				Steps.AddStep(5,false,1);
				break;
			case 321:
				Steps.AddStep(1,true,2);
				break;
			case 342:
				Steps.AddStep(4,false,1);
				Steps.AddStep(1,true,1);
				Steps.AddStep(4,true,1);
				break;
			case 324:
				Steps.AddStep(2,true,1);
				Steps.AddStep(1,true,2);
				break;
			case 352:
				Steps.AddStep(5,true,1);
				Steps.AddStep(1,false,1);
				Steps.AddStep(5,false,1);
				break;
			case 325:
				Steps.AddStep(2,false,1);
				Steps.AddStep(1,true,2);
				break;
			case 332:
				Steps.AddStep(2,true,1);
				Steps.AddStep(4,false,1);
				Steps.AddStep(1,true,1);
				Steps.AddStep(4,true,1);
				break;
			case 323:
				Steps.AddStep(2,true,2);
				Steps.AddStep(1,true,2);
				break;
			default:
				//Do nothing
				break;
			}
			
			for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
			{
				Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
			}
			Rubik.ChangeViewToColor(c[0]);
		}
	}
	
	public static void TopCorner() throws Exception
	{
		int CurrentStep=Steps.Count;
		int GetPos=0;
		int[] c=new int[5];
		int nTopPos=0;
		int nOtherPos=0;
		int nSum=0;
		boolean bFlag=false;
		
		c[0]=Rubik.Sides[0][1][1];
		c[1]=Rubik.Sides[1][1][1];
		c[2]=Rubik.Sides[5][1][1];
		c[3]=Rubik.Sides[3][1][1];
		c[4]=Rubik.Sides[4][1][1];
		
		for(int i=1;i<5;i++)
		{
			Rubik.ChangeViewToColor(c[0]);
			Rubik.ChangeViewFromTopByNextColor(c[i]);
			
			int j=i+1;
			if(j==5) j=1;
			GetPos=Rubik.FindCornerCell(c[0],c[i],c[j]);
			
			if(GetPos!=1015)
			{
				//1015 means already fixed
				if(GetPos<2000)
				{
					//In top layer, move to bottom first
					CurrentStep=Steps.Count;
					nTopPos=GetBit(GetPos,3);
					nSum=GetSum(GetPos)-1;
					nOtherPos=(nTopPos==0?GetBit(GetPos,1):(nSum-nTopPos));
					if(nOtherPos==5 && nSum==6) bFlag=false;
					if(nOtherPos==5 && nSum==8) bFlag=true;
					if(nOtherPos==3 && nSum==8) bFlag=false;
					if(nOtherPos==3 && nSum==7) bFlag=true;
					if(nOtherPos==4 && nSum==7) bFlag=false;
					if(nOtherPos==4 && nSum==5) bFlag=true;
					if(nOtherPos==1 && nSum==5) bFlag=false;
					if(nOtherPos==1 && nSum==6) bFlag=true;

					Steps.AddStep(nOtherPos, bFlag, 1);
					Steps.AddStep(2, !bFlag, 1);
					Steps.AddStep(nOtherPos, !bFlag, 1);
					
					for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
					{
						Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
					}
					Rubik.ChangeViewToColor(c[0]);
					Rubik.ChangeViewFromTopByNextColor(c[i]);
					GetPos=Rubik.FindCornerCell(c[0],c[i],c[j]);
				}
				
				//Now the block has move to bottom, or alreay at bottom
				CurrentStep=Steps.Count;
				nSum=GetSum(GetPos)-2-2; //1st"2":Bottom; 2nd"2":Layer 
				if(nSum==8) Steps.AddStep(2, false, 1);
				if(nSum==7) Steps.AddStep(2, true, 2);
				if(nSum==5) Steps.AddStep(2, true, 1);
				for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
				{
					Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
				}
				//Now the block is just under the bottom of current corner
				CurrentStep=Steps.Count;
				Rubik.ChangeViewToColor(c[0]);
				Rubik.ChangeViewFromTopByNextColor(c[i]);
				GetPos=Rubik.FindCornerCell(c[0],c[i],c[j]);
				nTopPos=GetBit(GetPos,3);
				nSum=GetSum(GetPos)-2-2;

				if(nSum!=6)
				{
					//Error
					return;
				}

				if(nTopPos==1)
				{
					Steps.AddStep(1, true, 1);
					Steps.AddStep(2, true, 1);
					Steps.AddStep(1, false, 1);
				}
				if(nTopPos==5)
				{
					Steps.AddStep(5, false, 1);
					Steps.AddStep(2, false, 1);
					Steps.AddStep(5, true, 1);
				}
				if(nTopPos==2)
				{
					Steps.AddStep(1, true, 1);
					Steps.AddStep(2, false,1);
					Steps.AddStep(1, false, 1);
					Steps.AddStep(5, false, 1);
					Steps.AddStep(2, true, 2);
					Steps.AddStep(5, true, 1);
				}
				
				for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
				{
					Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
				}
			}
		}
		Rubik.ChangeViewToColor(c[0]);
	}
	
	public static void SecondLayer() throws Exception
	{
		int CurrentStep=Steps.Count;
		int GetPos=0, cu=0,cb=0;  //CU:color on under side; CB: color on back side
		int Center1=0, Center2=0; //Center1, the rotate center; center2, theother center besides it 
		int ErrC1=0,ErrC2=0; //ErrC1 and Errc2, the border need to be fixed
		int[] c=new int[6];
		boolean bFlag=true, bClockWise=false;
		
		c[0]=Rubik.Sides[0][1][1];
		c[1]=Rubik.Sides[1][1][1];
		c[2]=Rubik.Sides[5][1][1];
		c[3]=Rubik.Sides[3][1][1];
		c[4]=Rubik.Sides[4][1][1];
		c[5]=Rubik.Sides[2][1][1];
		
		//select from the buttom to move to second layer
		while(bFlag)
		{
			bFlag=false;
			ErrC1=0;
			for(int i=1;i<5;i++)
			{
				Rubik.ChangeViewToColor(c[0]);
				Rubik.ChangeViewFromTopByNextColor(c[i]);
				cu=Rubik.Sides[2][1][0];
				cb=Rubik.Sides[1][1][2];

				if(cu!=c[5] && cb!=c[5])
				{
					//This one can be moved to second layer
					CurrentStep=Steps.Count;
					bFlag=true;
					Center1=Rubik.FindCenter(cu);
					Center2=Rubik.FindCenter(cb);
					//Move to the opposite
					if(Center1==1) Steps.AddStep(2, true, 2);
					if(Center1==5) Steps.AddStep(2, false, 1);
					if(Center1==4) Steps.AddStep(2, true, 1);
					//Set the direction of rotate
					if(Center1==1 && Center2==5) bClockWise=true;
					if(Center1==1 && Center2==4) bClockWise=false;
					if(Center1==5 && Center2==3) bClockWise=true;
					if(Center1==5 && Center2==1) bClockWise=false;
					if(Center1==3 && Center2==4) bClockWise=true;
					if(Center1==3 && Center2==5) bClockWise=false;
					if(Center1==4 && Center2==1) bClockWise=true;
					if(Center1==4 && Center2==3) bClockWise=false;
					//Start rotate
					Steps.AddStep(Center1, bClockWise, 1);
					Steps.AddStep(2, !bClockWise, 1);
					Steps.AddStep(Center1, !bClockWise, 1);
					Steps.AddStep(2, !bClockWise, 1);
					Steps.AddStep(Center2, !bClockWise, 1);
					Steps.AddStep(2, bClockWise, 1);
					Steps.AddStep(Center2, bClockWise, 1);
					//Actions
					for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
					{
						Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
					}
					Rubik.ChangeViewToColor(c[0]);
					break;
				}
				
				//Check if the sencond layer is ok
				if(!bFlag && ErrC1==0)
				{
					if(Rubik.Sides[1][1][1]!=Rubik.Sides[1][0][1] || Rubik.Sides[5][1][1]!=Rubik.Sides[5][1][2])
					{
						ErrC1=Rubik.Sides[1][1][1];
						ErrC2=Rubik.Sides[5][1][1];
					}
				}
			}
		}
		
		//Now All the blocks in bottom has been moved to second layer
		if(ErrC1>0)
		{
			//if there are still error blocks in second layer, move to bottom first
			Rubik.RotateRealSide(ErrC1, true, 1);
			Rubik.RotateRealSide(c[5], false, 1);
			Rubik.RotateRealSide(ErrC1, false, 1);
			Rubik.RotateRealSide(c[5], false, 1);
			Rubik.RotateRealSide(ErrC2, false, 1);
			Rubik.RotateRealSide(c[5], true, 1);
			Rubik.RotateRealSide(ErrC2, true, 1);
			Rubik.ChangeViewToColor(c[0]);
			SecondLayer();
		}
	}

	public static void BottomCross() throws Exception
	{
		int CurrentStep=Steps.Count;
		
		//Change view to the bottom
		int c=Rubik.Sides[2][1][1];
		int b[]=new int[4];
		int d[]={5,3,4,1};
		Rubik.ChangeViewToColor(c);
		
		int nSum=0, nPos=0;
		b[0]=Rubik.Sides[0][0][1];
		b[1]=Rubik.Sides[0][1][0];
		b[2]=Rubik.Sides[0][2][1];
		b[3]=Rubik.Sides[0][1][2];
		for(int i=0;i<4;i++)
		{
			if(b[i]==c)
			{
				nSum++;
				nPos+=d[i];
			}
		}
		
		if(nSum==4) return;
		if(nSum==0)
		{
			CurrentStep=Steps.Count;
			Steps.AddStep(1, false, 1);
			Steps.AddStep(4, false, 1);
			Steps.AddStep(0, false, 1);
			Steps.AddStep(4, true, 1);
			Steps.AddStep(0, true, 1);
			Steps.AddStep(1, true, 1);
			Steps.AddStep(3, false, 1);
			Steps.AddStep(0, false, 1);
			Steps.AddStep(5, false, 1);
			Steps.AddStep(0, true, 1);
			Steps.AddStep(5, true, 1);
			Steps.AddStep(3, true, 1);
		}
		if(nSum==2)
		{
			CurrentStep=Steps.Count;
			if(nPos==4) Rubik.ChangeViewFromTop(true);
			if(nPos==4 || nPos==9)
			{
				Steps.AddStep(1, false, 1);
				Steps.AddStep(4, false, 1);
				Steps.AddStep(0, false, 1);
				Steps.AddStep(4, true, 1);
				Steps.AddStep(0, true, 1);
				Steps.AddStep(1, true, 1);
			}
			else
			{
				if(nPos==6) Rubik.ChangeViewFromTop(false);
				if(nPos==7) Rubik.ChangeViewFromTop(true);
				if(nPos==5)
				{
					Rubik.ChangeViewFromTop(true);
					Rubik.ChangeViewFromTop(true);
				}
				Steps.AddStep(1, false, 1);
				Steps.AddStep(0, false, 1);
				Steps.AddStep(4, false, 1);
				Steps.AddStep(0, true, 1);
				Steps.AddStep(4, true, 1);
				Steps.AddStep(1, true, 1);
			}
		}
		for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
		{
			Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
		}
		Rubik.ChangeViewToColor(c);
	}
	
	public static void BottomCorner() throws Exception
	{
		int CurrentStep=Steps.Count;
		int c=Rubik.Sides[0][1][1];
		int b[]=new int[4];
		int d[]={5,3,4,1};
		int Pos2[]={-1,-1};
		int nSum=0, nPos=0, nFirst=0, nSecond=0;
		boolean bDirect=true, bMoreTime=false;
		b[0]=Rubik.Sides[0][0][2];
		b[1]=Rubik.Sides[0][0][0];
		b[2]=Rubik.Sides[0][2][0];
		b[3]=Rubik.Sides[0][2][2];
		for(int i=0;i<4;i++)
		{
			if(b[i]==c)
			{
				nSum++;
				nPos=d[i];
			}
			else
			{
				if(Pos2[0]==-1) Pos2[0]=i;
				else Pos2[1]=i;
			}
		}
		
		if(nSum==4) return;
		if(nSum==1)
		{
			Rubik.ChangeViewFromTopByNextColor(Rubik.Sides[nPos][1][1]);
			bDirect=(Rubik.Sides[1][0][0]==c);
			if(bDirect) nFirst=5;
			else nFirst=3;
			Steps.Clear();
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(0, bDirect, 1);
			Steps.AddStep(nFirst, !bDirect, 1);
			Steps.AddStep(0, bDirect, 1);
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(0, bDirect, 2);
			Steps.AddStep(nFirst, !bDirect, 1);
		}
		if(nSum==2)
		{
			for(int i=0;i<Pos2[0];i++) Rubik.ChangeViewFromTop(true);
			bDirect=(Rubik.Sides[1][0][0]==c);
			nFirst=bDirect?5:1;
			nSecond=6-nFirst;
			Steps.Clear();
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(nSecond, !bDirect, 1);
			Steps.AddStep(nFirst, !bDirect, 1);
			Steps.AddStep(nSecond, bDirect, 1);
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(nSecond, !bDirect, 1);
			Steps.AddStep(nFirst, !bDirect, 1);
			Steps.AddStep(nSecond, bDirect, 1);
			Steps.AddStep(0, true, Pos2[1]-Pos2[0]);
			Steps.AddStep(nSecond, !bDirect, 1);
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(nSecond, bDirect, 1);
			Steps.AddStep(nFirst, !bDirect, 1);
			Steps.AddStep(nSecond, !bDirect, 1);
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(nSecond, bDirect, 1);
			Steps.AddStep(nFirst, !bDirect, 1);
		}
		if(nSum==0)
		{
			bMoreTime=true;
			for(int i=0;i<4;i++)
			{
				if(Rubik.Sides[1][0][0]==c && Rubik.Sides[1][2][0]==c) break;
				Rubik.ChangeViewFromTop(true);
			}
			nFirst=3;
			bDirect=false;
			Steps.Clear();
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(0, bDirect, 1);
			Steps.AddStep(nFirst, !bDirect, 1);
			Steps.AddStep(0, bDirect, 1);
			Steps.AddStep(nFirst, bDirect, 1);
			Steps.AddStep(0, bDirect, 2);
			Steps.AddStep(nFirst, !bDirect, 1);
		}
		for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
		{
			Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
		}
		Rubik.ChangeViewToColor(c);
		if(bMoreTime) BottomCorner();
	}
	
	public static void ThirdLayerCorner() throws Exception
	{
		int CurrentStep=Steps.Count;
		int c=Rubik.Sides[0][1][1];
		boolean bMoreTime=true;

		for(int i=0;i<4;i++)
		{
			if(Rubik.Sides[1][0][0] == Rubik.Sides[1][2][0])
			{
				if(Rubik.Sides[5][2][0] == Rubik.Sides[5][2][2]) return;
				else
				{
					bMoreTime=false;
					break;
				}
			}
			Rubik.ChangeViewFromTop(true);
		}

		Steps.AddStep(5, true, 1);
		Steps.AddStep(3, false, 1);
		Steps.AddStep(5, true, 1);
		Steps.AddStep(1, true, 2);
		Steps.AddStep(5, false, 1);
		Steps.AddStep(3, true, 1);
		Steps.AddStep(5, true, 1);
		Steps.AddStep(1, true, 2);
		Steps.AddStep(5, true, 2);
		
		for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
		{
			Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
		}

		Rubik.ChangeViewToColor(c);
		if(bMoreTime) ThirdLayerCorner();
	}
	
	public static void ThirdLayerCornerSnap() throws Exception
	{
		int c=Rubik.Sides[0][1][1];
		int nBorderColor=Rubik.Sides[1][0][0];
		int nCenter=Rubik.FindCenter(nBorderColor);
		int CurrentStep=Steps.Count;

		if(nCenter==5) Steps.AddStep(0, false, 1);
		if(nCenter==3) Steps.AddStep(0, false, 2);
		if(nCenter==4) Steps.AddStep(0, true, 1);
		
		for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
		{
			Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
		}
		
		Rubik.ChangeViewToColor(c);
	}

	public static void ThirdLayerBorderSnap() throws Exception
	{
		int c=Rubik.Sides[0][1][1];
		boolean bMoreTime=true;
		boolean bDirect=true;

		for(int i=0;i<4;i++)
		{
			if(Rubik.Sides[1][0][0] == Rubik.Sides[1][1][0])
			{
				if(Rubik.Sides[5][2][0] == Rubik.Sides[5][2][1]) return;
				else
				{
					bDirect=(Rubik.Sides[5][2][1]==Rubik.Sides[4][1][1]);
					bMoreTime=false;
					break;
				}
			}
			Rubik.ChangeViewFromTop(true);
		}
		
		int CurrentStep=Steps.Count;
		
		Steps.AddStep(3, true, 2);
		Steps.AddStep(0, bDirect, 1);
		Steps.AddStep(4, false, 1);
		Steps.AddStep(5, true, 1);
		Steps.AddStep(3, true, 2);
		Steps.AddStep(5, false, 1);
		Steps.AddStep(4, true, 1);
		Steps.AddStep(0, bDirect, 1);
		Steps.AddStep(3, true, 2);
		
		for(Steps.Index=CurrentStep;Steps.Index<Steps.Count;Steps.Index++)
		{
			Rubik.RotateRealSide(Steps.Color(), Steps.ClockWise(), Steps.Quarter());
		}

		Rubik.ChangeViewToColor(c);
		if(bMoreTime) ThirdLayerBorderSnap();
	}
	
	public static int GetBit(int n, int m)
	{
		double nFilter=Math.pow(10.,m-1);
		n=(int) Math.floor(n/nFilter);
		return n%10;
	}
	
	public static int GetSum(int n)
	{
		int nReturn=0;
		while(n>0)
		{
			nReturn+=n%10;
			n=(n-n%10)/10;
		}
		return nReturn;
	}
	
	public static class Rubik
	{
		static int[][][] Sides=new int [7][3][3];
		static int[] Position=new int[6];
		
		public static String ImportColor(String[] SideColors)
		{
			try
			{
				int Check[]={0,0,0,0,0,0};
				for(int i=0;i<6;i++)
				{
					String szColors=SideColors[i];
					if(szColors.length()!=9) return "len(Side"+i+")="+szColors.length();
					for(int j=0;j<9;j++)
					{
						int nColor=Color.Value(szColors.substring(j,j+1));
						if(nColor==0) return "Side"+i+","+j+"="+szColors.substring(j,j+1);
						int y=j%3;
						int x=(j-y)/3;
						Check[nColor-1]++;
						Sides[i][x][y]=nColor;
						if(j==4) Position[i]=nColor;
					}
				}
				for(int i=0;i<6;i++)
				{
					if(Check[i]!=9) return "Check"+i+"="+Check[i];
				}
				return "";
			}
			catch(Exception e)
			{
				return e.getMessage();
			}
		}
	
		public static void ChangeViewToColor(int nColor) throws Exception
		{
			int nNewCenter=-1;
			for(int i=0;i<6;i++)
			{
				if(Sides[i][1][1]==nColor) nNewCenter=i;
			}
			
			if(nNewCenter==0) return;
			if(nNewCenter==1)
			{
				CopyMatrics(0,6,0);
				CopyMatrics(1,0,0);
				CopyMatrics(2,1,0);
				CopyMatrics(3,2,0);
				CopyMatrics(6,3,0);
				CopyMatrics(5,6,0);
				CopyMatrics(6,5,1);
				CopyMatrics(4,6,0);
				CopyMatrics(6,4,2);
			}
			if(nNewCenter==2)
			{
				CopyMatrics(0,6,0);
				CopyMatrics(2,0,0);
				CopyMatrics(6,2,0);
				CopyMatrics(1,6,0);
				CopyMatrics(3,1,0);
				CopyMatrics(6,3,0);
				CopyMatrics(5,6,0);
				CopyMatrics(6,5,3);
				CopyMatrics(4,6,0);
				CopyMatrics(6,4,3);
			}
			if(nNewCenter==3)
			{
				CopyMatrics(0,6,0);
				CopyMatrics(3,0,0);
				CopyMatrics(2,3,0);
				CopyMatrics(1,2,0);
				CopyMatrics(6,1,0);
				CopyMatrics(5,6,0);
				CopyMatrics(6,5,2);
				CopyMatrics(4,6,0);
				CopyMatrics(6,4,1);
			}
			if(nNewCenter==4)
			{
				CopyMatrics(0,6,0);
				CopyMatrics(4,0,0);
				CopyMatrics(2,4,3);
				CopyMatrics(5,2,3);
				CopyMatrics(6,5,0);
				CopyMatrics(3,6,0);
				CopyMatrics(6,3,2);
				CopyMatrics(1,6,0);
				CopyMatrics(6,1,1);
			}
			if(nNewCenter==5)
			{
				CopyMatrics(0,6,0);
				CopyMatrics(5,0,0);
				CopyMatrics(2,5,3);
				CopyMatrics(4,2,3);
				CopyMatrics(6,4,0);
				CopyMatrics(3,6,0);
				CopyMatrics(6,3,1);
				CopyMatrics(1,6,0);
				CopyMatrics(6,1,2);
			}
		}
		
		public static void CopyMatrics(int A, int B, int n) throws Exception
		{
			//Copy from A to B
			//n=0:DirectCopy  1:ClockWiseCopy  2:AntiClockCop  3:Reverse copy
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(n==0) Sides[B][i][j]=Sides[A][i][j];
					if(n==1) Sides[B][j][2-i]=Sides[A][i][j];
					if(n==2) Sides[B][2-j][i]=Sides[A][i][j];
					if(n==3) Sides[B][i][j]=Sides[A][2-i][2-j];
				}
			}
		}
	
		public static void ChangeViewFromTopByNextColor(int nColor) throws Exception
		{
			int nNextColorCenter=-1;
			for(int i=0;i<6;i++)
			{
				if(Sides[i][1][1]==nColor) nNextColorCenter=i;
			}
			
			if(nNextColorCenter==4) ChangeViewFromTop(false);
			if(nNextColorCenter==5) ChangeViewFromTop(true);
			if(nNextColorCenter==3)
			{
				ChangeViewFromTop(true);
				ChangeViewFromTop(true);
			}
		}
		
		public static void ChangeViewFromTop(boolean ClockWise) throws Exception
		{
			int Next1=3,Next2=1;
			if(!ClockWise) {Next1=1;Next2=3;}
			
			int nType=ClockWise?1:2;
			CopyMatrics(0,6,0);
			CopyMatrics(6,0,nType);
			CopyMatrics(5,6,0);
			CopyMatrics(Next1,5,nType);
			CopyMatrics(4,Next1,nType);
			CopyMatrics(Next2,4,nType);
			CopyMatrics(6,Next2,nType);
			CopyMatrics(2,6,0);
			CopyMatrics(6,2,3-nType);
		}

		public static void RotateBottomSide(boolean ClockWise) throws Exception
		{
			int temp=0;
			int i;
			
			CopyMatrics(2,6,ClockWise?2:1); //Bottom ClockWise = Top Anti-ClockWise
			CopyMatrics(6,2,0);
			if(ClockWise)
			{
				for(i=0;i<3;i++)
				{
					temp=Sides[5][0][i];
					Sides[5][0][i]=Sides[3][2-i][0];
					Sides[3][2-i][0]=Sides[4][2][2-i];
					Sides[4][2][2-i]=Sides[1][i][2];
					Sides[1][i][2]=temp;
				}
			}
			else
			{
				for(i=0;i<3;i++)
				{
					temp=Sides[5][0][i];
					Sides[5][0][i]=Sides[1][i][2];
					Sides[1][i][2]=Sides[4][2][2-i];
					Sides[4][2][2-i]=Sides[3][2-i][0];
					Sides[3][2-i][0]=temp;
				}
			}
		}
		
		public static void RotateRealSide(int nColor, boolean ClockWise, int nQuarter) throws Exception
		{
			//ClockWise means AntiClockWise from Top, and nCount>0 in Robot Class
			int nCenter=-1;
			int[] arOpposite={2,3,0,1,5,4};
			for(int i=0;i<6;i++)
			{
				if(Position[i]==nColor) nCenter=i;
			}
			int nOppositeColor=Position[arOpposite[nCenter]];
			
			if(nCenter==2)
			{
				//Do Nothing
			}
			if(nCenter==1)
			{
				RotatePaw();
			}
			if(nCenter==0)
			{
				RotatePaw();
				RotatePaw();
			}
			if(nCenter==3)
			{
				RotateBottom(false);
				RotateBottom(false);
				RotatePaw();
			}
			if(nCenter==4)
			{
				RotateBottom(false);
				RotatePaw();
			}
			if(nCenter==5)
			{
				RotateBottom(true);
				RotatePaw();
			}
			ChangeViewToColor(nOppositeColor);
			
			for(int i=0;i<nQuarter;i++)
			{
				RotateBottomSide(!ClockWise);
			}
		}	

		public static void RotateBottom(boolean ClockWise) throws Exception
		{
			if(ClockWise)
			{
				int n=Position[5];
				Position[5]=Position[3];
				Position[3]=Position[4];
				Position[4]=Position[1];
				Position[1]=n;
			}
			else
			{
				int n=Position[5];
				Position[5]=Position[1];
				Position[1]=Position[4];
				Position[4]=Position[3];
				Position[3]=n;
			}
		}
		
		public static void RotatePaw() throws Exception
		{
			//Only can move forward
			int n=Position[0];
			Position[0]=Position[3];
			Position[3]=Position[2];
			Position[2]=Position[1];
			Position[1]=n;
		}
		
		public static int FindCenter(int nColor) throws Exception
		{
			int nCenter=-1;
			for(int i=0;i<6;i++)
			{
				if(Sides[i][1][1]==nColor) nCenter=i;
			}
			return nCenter;
		}
			
		public static int FindCornerCell(int Color1, int Color2, int Color3)
		{
			//return value=nLayer*1000+nFirstColorCenter*100+nSecondColorCenter*10+nThirdCenter
			int nReturn=0;
			int nCheck=Color1*100+Color2*10+Color3;
			
			//First Layer
			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[0][0][2],Sides[5][2][2],Sides[1][0][0],0,5,1);
			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[0][0][0],Sides[5][2][0],Sides[3][0][2],0,5,3);
			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[0][2][0],Sides[4][0][0],Sides[3][2][2],0,4,3);
			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[0][2][2],Sides[4][0][2],Sides[1][2][0],0,4,1);
			if(nReturn>0) return 1000+nReturn;

			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[2][0][0],Sides[5][0][2],Sides[1][0][2],2,5,1);
			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[2][0][2],Sides[5][0][0],Sides[3][0][0],2,5,3);
			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[2][2][2],Sides[4][2][0],Sides[3][2][0],2,4,3);
			if(nReturn==0) nReturn=CheckCorner(nCheck,Sides[2][2][0],Sides[4][2][2],Sides[1][2][2],2,4,1);
			if(nReturn>0) return 2000+nReturn;
			return 0;
		}

		public static int CheckCorner(int CheckValue, int c1, int c2, int c3, int p1, int p2, int p3)
		{
			if(c1*100+c2*10+c3 == CheckValue) return p1*100+p2*10+p3;
			if(c1*100+c3*10+c2 == CheckValue) return p1*100+p3*10+p2;
			if(c2*100+c1*10+c3 == CheckValue) return p2*100+p1*10+p3;
			if(c2*100+c3*10+c1 == CheckValue) return p2*100+p3*10+p1;
			if(c3*100+c1*10+c2 == CheckValue) return p3*100+p1*10+p2;
			if(c3*100+c2*10+c1 == CheckValue) return p3*100+p2*10+p1;
			return 0;
		}
		
		public static int FindBorderCell(int Color1, int Color2)
		{
			//return value=nLayer*100+nFirstColorCenter*10+nSecondColorCenter
			int nReturn=0;
			int c1=0,c2=0;
			//First layer
			c1=Sides[0][0][1]; c2=Sides[5][2][1];
			if(c1==Color1 && c2==Color2) return 105;
			if(c1==Color2 && c2==Color1) return 150;
			c1=Sides[0][1][0]; c2=Sides[3][1][2];
			if(c1==Color1 && c2==Color2) return 103;
			if(c1==Color2 && c2==Color1) return 130;
			c1=Sides[0][2][1]; c2=Sides[4][0][1];
			if(c1==Color1 && c2==Color2) return 104;
			if(c1==Color2 && c2==Color1) return 140;
			c1=Sides[0][1][2]; c2=Sides[1][1][0];
			if(c1==Color1 && c2==Color2) return 101;
			if(c1==Color2 && c2==Color1) return 110;
			//Seconde Layer
			c1=Sides[4][1][2]; c2=Sides[1][2][1];
			if(c1==Color1 && c2==Color2) return 241;
			if(c1==Color2 && c2==Color1) return 214;			
			c1=Sides[4][1][0]; c2=Sides[3][2][1];
			if(c1==Color1 && c2==Color2) return 243;
			if(c1==Color2 && c2==Color1) return 234;			
			c1=Sides[5][1][0]; c2=Sides[3][0][1];
			if(c1==Color1 && c2==Color2) return 253;
			if(c1==Color2 && c2==Color1) return 235;			
			c1=Sides[5][1][2]; c2=Sides[1][0][1];
			if(c1==Color1 && c2==Color2) return 251;
			if(c1==Color2 && c2==Color1) return 215;
			//Third Layer
			c1=Sides[4][2][1]; c2=Sides[2][2][1];
			if(c1==Color1 && c2==Color2) return 342;
			if(c1==Color2 && c2==Color1) return 324;			
			c1=Sides[1][1][2]; c2=Sides[2][1][0];
			if(c1==Color1 && c2==Color2) return 312;
			if(c1==Color2 && c2==Color1) return 321;			
			c1=Sides[5][0][1]; c2=Sides[2][0][1];
			if(c1==Color1 && c2==Color2) return 352;
			if(c1==Color2 && c2==Color1) return 325;			
			c1=Sides[3][1][0]; c2=Sides[2][1][2];
			if(c1==Color1 && c2==Color2) return 332;
			if(c1==Color2 && c2==Color1) return 323;
			return 0;
		}

	}
	
	public static class Steps
	{
		public static int Count=0;
		public static int Index=-1;
		private static int[] arColor=new int[200];
		private static boolean[] arClockWise=new boolean[200];
		private static int[] arQuarter=new int[200];
		public static void Clear()
		{
			Count=0;
			Index=-1;
		}
		public static void AddStep(int SideNum, boolean ClockWise, int Quarter)
		{
			arColor[Count]=Rubik.Sides[SideNum][1][1];
			arClockWise[Count]=ClockWise;
			arQuarter[Count]=Quarter;
			Count++;
		}
		public static int Color()
		{
			return arColor[Index];
		}
		public static boolean ClockWise()
		{
			return arClockWise[Index];
		}
		public static int Quarter()
		{
			return arQuarter[Index];
		}
	}

	public static class Color
	{
		public static int Y=1;
		public static int B=2;
		public static int R=3;
		public static int W=4;
		public static int O=5;
		public static int G=6;
		
		public static String Text(int n)
		{
			switch(n)
			{
			case 1:
				return "Y";
			case 2:
				return "B";
			case 3:
				return "R";
			case 4:
				return "W";
			case 5:
				return "O";
			case 6:
				return "G";
			default:
				return n+"";
			}
		}
		
		public static int Value(String c)
		{
			if(c.equals("Y")||c.equals("y")) return 1;
			if(c.equals("B")||c.equals("b")) return 2;
			if(c.equals("R")||c.equals("r")) return 3;
			if(c.equals("W")||c.equals("w")) return 4;
			if(c.equals("O")||c.equals("o")) return 5;
			if(c.equals("G")||c.equals("g")) return 6;
			return 0;
		}
	}
}


