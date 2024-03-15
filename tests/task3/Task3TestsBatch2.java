package csen1002.tests.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task3.FallbackDfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task3TestsBatch2 {

	@Test
	public void testFallbackDfa1String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#i;k;l;m;o#0,i,2;0,k,5;0,l,2;0,m,8;0,o,9;1,i,8;1,k,6;1,l,6;1,m,0;1,o,1;2,i,6;2,k,1;2,l,2;2,m,3;2,o,7;3,i,1;3,k,9;3,l,6;3,m,8;3,o,4;4,i,2;4,k,6;4,l,1;4,m,1;4,o,6;5,i,1;5,k,9;5,l,8;5,m,8;5,o,3;6,i,7;6,k,1;6,l,2;6,m,2;6,o,5;7,i,8;7,k,1;7,l,2;7,m,9;7,o,3;8,i,3;8,k,0;8,l,1;8,m,4;8,o,4;9,i,5;9,k,6;9,l,1;9,m,3;9,o,1#6#5;7");
		assertEquals("klklo,5;lilmki,5;o,5;i,7", fallbackDfa.run("klklolilmkioi"));
	}

	@Test
	public void testFallbackDfa1String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#i;k;l;m;o#0,i,2;0,k,5;0,l,2;0,m,8;0,o,9;1,i,8;1,k,6;1,l,6;1,m,0;1,o,1;2,i,6;2,k,1;2,l,2;2,m,3;2,o,7;3,i,1;3,k,9;3,l,6;3,m,8;3,o,4;4,i,2;4,k,6;4,l,1;4,m,1;4,o,6;5,i,1;5,k,9;5,l,8;5,m,8;5,o,3;6,i,7;6,k,1;6,l,2;6,m,2;6,o,5;7,i,8;7,k,1;7,l,2;7,m,9;7,o,3;8,i,3;8,k,0;8,l,1;8,m,4;8,o,4;9,i,5;9,k,6;9,l,1;9,m,3;9,o,1#6#5;7");
		assertEquals("oioliolmmoklo,7;o,5;kmk,5;o,5", fallbackDfa.run("oioliolmmoklookmko"));
	}

	@Test
	public void testFallbackDfa1String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#i;k;l;m;o#0,i,2;0,k,5;0,l,2;0,m,8;0,o,9;1,i,8;1,k,6;1,l,6;1,m,0;1,o,1;2,i,6;2,k,1;2,l,2;2,m,3;2,o,7;3,i,1;3,k,9;3,l,6;3,m,8;3,o,4;4,i,2;4,k,6;4,l,1;4,m,1;4,o,6;5,i,1;5,k,9;5,l,8;5,m,8;5,o,3;6,i,7;6,k,1;6,l,2;6,m,2;6,o,5;7,i,8;7,k,1;7,l,2;7,m,9;7,o,3;8,i,3;8,k,0;8,l,1;8,m,4;8,o,4;9,i,5;9,k,6;9,l,1;9,m,3;9,o,1#6#5;7");
		assertEquals("kmoiklmk,5;o,5;o,5;m,2", fallbackDfa.run("kmoiklmkoom"));
	}

	@Test
	public void testFallbackDfa1String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#i;k;l;m;o#0,i,2;0,k,5;0,l,2;0,m,8;0,o,9;1,i,8;1,k,6;1,l,6;1,m,0;1,o,1;2,i,6;2,k,1;2,l,2;2,m,3;2,o,7;3,i,1;3,k,9;3,l,6;3,m,8;3,o,4;4,i,2;4,k,6;4,l,1;4,m,1;4,o,6;5,i,1;5,k,9;5,l,8;5,m,8;5,o,3;6,i,7;6,k,1;6,l,2;6,m,2;6,o,5;7,i,8;7,k,1;7,l,2;7,m,9;7,o,3;8,i,3;8,k,0;8,l,1;8,m,4;8,o,4;9,i,5;9,k,6;9,l,1;9,m,3;9,o,1#6#5;7");
		assertEquals("o,5;i,7;i,7;limmm,8", fallbackDfa.run("oiilimmm"));
	}

	@Test
	public void testFallbackDfa1String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#i;k;l;m;o#0,i,2;0,k,5;0,l,2;0,m,8;0,o,9;1,i,8;1,k,6;1,l,6;1,m,0;1,o,1;2,i,6;2,k,1;2,l,2;2,m,3;2,o,7;3,i,1;3,k,9;3,l,6;3,m,8;3,o,4;4,i,2;4,k,6;4,l,1;4,m,1;4,o,6;5,i,1;5,k,9;5,l,8;5,m,8;5,o,3;6,i,7;6,k,1;6,l,2;6,m,2;6,o,5;7,i,8;7,k,1;7,l,2;7,m,9;7,o,3;8,i,3;8,k,0;8,l,1;8,m,4;8,o,4;9,i,5;9,k,6;9,l,1;9,m,3;9,o,1#6#5;7");
		assertEquals("ollllilokkkikoki,7;o,5;mo,7;o,5", fallbackDfa.run("ollllilokkkikokiomoo"));
	}

	@Test
	public void testFallbackDfa2String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#c;q;r;y#0,c,0;0,q,6;0,r,10;0,y,0;1,c,13;1,q,13;1,r,9;1,y,3;2,c,5;2,q,0;2,r,3;2,y,4;3,c,12;3,q,6;3,r,2;3,y,1;4,c,13;4,q,10;4,r,5;4,y,10;5,c,13;5,q,11;5,r,3;5,y,4;6,c,10;6,q,8;6,r,5;6,y,6;7,c,0;7,q,1;7,r,12;7,y,0;8,c,11;8,q,3;8,r,9;8,y,0;9,c,2;9,q,2;9,r,3;9,y,5;10,c,8;10,q,9;10,r,3;10,y,7;11,c,10;11,q,6;11,r,12;11,y,10;12,c,10;12,q,3;12,r,7;12,y,10;13,c,13;13,q,4;13,r,1;13,y,2#13#0;2;9");
		assertEquals("rrrcqr,2;y,2;y,2;rqryrq,0", fallbackDfa.run("rrrcqryyrqryrq"));
	}

	@Test
	public void testFallbackDfa2String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#c;q;r;y#0,c,0;0,q,6;0,r,10;0,y,0;1,c,13;1,q,13;1,r,9;1,y,3;2,c,5;2,q,0;2,r,3;2,y,4;3,c,12;3,q,6;3,r,2;3,y,1;4,c,13;4,q,10;4,r,5;4,y,10;5,c,13;5,q,11;5,r,3;5,y,4;6,c,10;6,q,8;6,r,5;6,y,6;7,c,0;7,q,1;7,r,12;7,y,0;8,c,11;8,q,3;8,r,9;8,y,0;9,c,2;9,q,2;9,r,3;9,y,5;10,c,8;10,q,9;10,r,3;10,y,7;11,c,10;11,q,6;11,r,12;11,y,10;12,c,10;12,q,3;12,r,7;12,y,10;13,c,13;13,q,4;13,r,1;13,y,2#13#0;2;9");
		assertEquals("cqyyqqqyrryqyyqcrr,2;y,2;y,2;cqq,10", fallbackDfa.run("cqyyqqqyrryqyyqcrryycqq"));
	}

	@Test
	public void testFallbackDfa2String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#c;q;r;y#0,c,0;0,q,6;0,r,10;0,y,0;1,c,13;1,q,13;1,r,9;1,y,3;2,c,5;2,q,0;2,r,3;2,y,4;3,c,12;3,q,6;3,r,2;3,y,1;4,c,13;4,q,10;4,r,5;4,y,10;5,c,13;5,q,11;5,r,3;5,y,4;6,c,10;6,q,8;6,r,5;6,y,6;7,c,0;7,q,1;7,r,12;7,y,0;8,c,11;8,q,3;8,r,9;8,y,0;9,c,2;9,q,2;9,r,3;9,y,5;10,c,8;10,q,9;10,r,3;10,y,7;11,c,10;11,q,6;11,r,12;11,y,10;12,c,10;12,q,3;12,r,7;12,y,10;13,c,13;13,q,4;13,r,1;13,y,2#13#0;2;9");
		assertEquals("crccrcrcqqrrcyyq,9;y,2;y,2;rq,13", fallbackDfa.run("crccrcrcqqrrcyyqyyrq"));
	}

	@Test
	public void testFallbackDfa2String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#c;q;r;y#0,c,0;0,q,6;0,r,10;0,y,0;1,c,13;1,q,13;1,r,9;1,y,3;2,c,5;2,q,0;2,r,3;2,y,4;3,c,12;3,q,6;3,r,2;3,y,1;4,c,13;4,q,10;4,r,5;4,y,10;5,c,13;5,q,11;5,r,3;5,y,4;6,c,10;6,q,8;6,r,5;6,y,6;7,c,0;7,q,1;7,r,12;7,y,0;8,c,11;8,q,3;8,r,9;8,y,0;9,c,2;9,q,2;9,r,3;9,y,5;10,c,8;10,q,9;10,r,3;10,y,7;11,c,10;11,q,6;11,r,12;11,y,10;12,c,10;12,q,3;12,r,7;12,y,10;13,c,13;13,q,4;13,r,1;13,y,2#13#0;2;9");
		assertEquals("ccrcy,2;yqyrq,9;y,2;cqyr,3", fallbackDfa.run("ccrcyyqyrqycqyr"));
	}

	@Test
	public void testFallbackDfa2String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#c;q;r;y#0,c,0;0,q,6;0,r,10;0,y,0;1,c,13;1,q,13;1,r,9;1,y,3;2,c,5;2,q,0;2,r,3;2,y,4;3,c,12;3,q,6;3,r,2;3,y,1;4,c,13;4,q,10;4,r,5;4,y,10;5,c,13;5,q,11;5,r,3;5,y,4;6,c,10;6,q,8;6,r,5;6,y,6;7,c,0;7,q,1;7,r,12;7,y,0;8,c,11;8,q,3;8,r,9;8,y,0;9,c,2;9,q,2;9,r,3;9,y,5;10,c,8;10,q,9;10,r,3;10,y,7;11,c,10;11,q,6;11,r,12;11,y,10;12,c,10;12,q,3;12,r,7;12,y,10;13,c,13;13,q,4;13,r,1;13,y,2#13#0;2;9");
		assertEquals("cyqrryryrrccrqcqyq,9;y,2;y,2;y,2", fallbackDfa.run("cyqrryryrrccrqcqyqyyy"));
	}

}