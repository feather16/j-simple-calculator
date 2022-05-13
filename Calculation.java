public class Calculation{
	
	String F = new String();
	
	public Calculation(String s){
		
		if(s.length()==0){
			F="";
			return;
		}
		
		if(isSymbol(s.charAt(0))&&s.charAt(0)!='-') s="Error";
		
		if(isSymbol(s.charAt(s.length()-1))) s="Error";
		
		int Par=0;
		for(char x: s.toCharArray()){
			if(x=='(') Par++;
			if(x==')') Par--;
			if(Par<0) break;
		}
		if(Par!=0) s="Error";
		
		for(int i=0;i<s.length()-1;i++){
			if(isSymbol(s.charAt(i))&&isSymbol(s.charAt(i+1))){
				s="Error";
				break;
			}
		}
		if(!s.equals("Error"))System.out.println("Start to calculate: "+s);
		F=AddAndSub(s);
	}
	
	public String AddAndSub(String s){
		if(MatchNumberOutsideofPar(s,'+')+MatchNumberOutsideofPar(s,'-')==0){
			String Result = MultAndDiv(s);
			if(Result.equals("Error")) return "Error";
			return Result;
		}
		else if(s.charAt(0)=='-'&&MatchNumberOutsideofPar(s,'+')+MatchNumberOutsideofPar(s,'-')==1){
			String Result = MultAndDiv(s);
			if(Result.equals("Error")) return "Error";
			return Result;
		}
		else{
			int count=0,Par=0;
			for(char x: s.toCharArray()){
				if(x=='(') Par++;
				if(x==')') Par--;
				if(x=='+'&&Par==0){
					String s1 = new String(s.substring(0,count));
					String s2 = new String(s.substring(count+1,s.length()));
					System.out.println("In AddAndSub: "+s+" -> ["+s1+"]+["+s2+"]");
					String Result1 = MultAndDiv(s1);
					String Result2 = AddAndSub(s2);
					if(Result1.equals("Error")||Result2.equals("Error")) return "Error";
					int n1=Integer.parseInt(Result1);
					int n2=Integer.parseInt(Result2);
					return String.valueOf(n1+n2);
				}
				if(x=='-'&&Par==0&&count!=0){
					String s1 = new String(s.substring(0,count));
					String s2 = new String(s.substring(count+1,s.length()));
					System.out.println("In AddAndSub: "+s+" -> ["+s1+"]-["+s2+"]");
					String Result1 = MultAndDiv(s1);
					String Result2 = AddAndSub(s2);
					if(Result1.equals("Error")||Result2.equals("Error")) return "Error";
					int n1=Integer.parseInt(Result1);
					int n2=Integer.parseInt(Result2);
					return String.valueOf(n1-n2);
				}
				count++;
			}
		}
		System.out.println("# Failed in AddAndSub. #");
		return "# Failed in AddAndSub. #";
	}
	
	public String MultAndDiv(String s){
		if(MatchNumberOutsideofPar(s,'\u00D7')+MatchNumberOutsideofPar(s,'\u00F7')==0){
			String Result = Const(s);
			if(Result.equals("Error")) return "Error";
			return Result;
		}
		
		int count=0,Par=0;
		for(char x: s.toCharArray()){
			if(x=='(') Par++;
			if(x==')') Par--;
			if(x=='\u00D7'&&Par==0){
				String s1 = new String(s.substring(0,count));
				String s2 = new String(s.substring(count+1,s.length()));
				System.out.println("In MultAndDiv: "+s+" -> ["+s1+"]\u00D7["+s2+"]");
				String Result1 = Const(s1);
				String Result2 = MultAndDiv(s2);
				if(Result1.equals("Error")||Result2.equals("Error")) return "Error";
				int n1=Integer.parseInt(Result1);
				int n2=Integer.parseInt(Result2);
				return String.valueOf(n1*n2);
			}
			if(x=='\u00F7'&&Par==0){
				String s1 = new String(s.substring(0,count));
				String s2 = new String(s.substring(count+1,s.length()));
				System.out.println("In MultAndDiv: "+s+" -> ["+s1+"]\u00F7["+s2+"]");
				String Result1 = Const(s1);
				String Result2 = MultAndDiv(s2);
				if(Result1.equals("Error")||Result2.equals("Error")) return "Error";
				int n1=Integer.parseInt(Result1);
				int n2=Integer.parseInt(Result2);
				try{
					return String.valueOf(n1/n2);
				} catch (ArithmeticException e) {
					return "Error";
				}
			}
			count++;
		}
		System.out.println("# Failed in MultAndDiv. #");
		return "# Failed in MultAndDiv. #";
	}
	
	public String Const(String s){
		if(ParExist(s)){
			System.out.print("In Const: "+s+" -> ");
			s=s.substring(1,s.length()-1);
			System.out.println(s);
			String Result = AddAndSub(s);
			if(Result.equals("Error")) return "Error";
			return Result;
		}
		else{
			return s;
		}
	}
	
	public int MatchNumberOutsideofPar(String s,char target){
		int ret=0,Par=0;
		for(char x: s.toCharArray()){
			if(x=='(') Par++;
			if(x==')') Par--;
			if(x==target&&Par==0) ret++;
		}
		return ret;
	}
	
	public boolean ParExist(String s){
		for(char x: s.toCharArray()){
			if(x=='('||x==')') return true;
		}
		return false;
	}
	
	public boolean isSymbol(char c){
		if(c=='+'||c=='-'||c=='\u00D7'||c=='\u00F7') return true;
		return false;
	}
	
	public String output(){
		System.out.println("-> "+F+"\n");
		return F;
	}
}