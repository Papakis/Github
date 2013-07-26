/*package aspectj;

public aspect DemoAspect {
	pointcut anyName():
		call(void aspectj.AOPDemo.method1(*,*));
	pointcut anyName2():
		call(void aspectj.AOPDemo.*(..));
	before() : anyName2(){
		System.out.println("Demo Aspect Pointcut - before ...");
		}
	after() : anyName() {
		System.out.println("Demo Aspect Pointcut Method 1 - after ...");
	}
}*/