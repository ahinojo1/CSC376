public class ThreadTry2 implements Runnable{
	String txt;
	public ThreadTry2(String txt){
		this.txt = txt;
	}
	public void run(){
		System.out.println(txt);
	}
}