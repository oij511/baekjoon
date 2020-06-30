package com.baekjoon.je25th;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Baek11279 {

	static ArrayList<Integer> heap = new ArrayList<>();
	//i의  좌자식 : 2i 우자식: 2i+1
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		heap.add(0);//인덱스1부터 시작하기위한 전처리
		
		int count = Integer.parseInt(br.readLine());
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				bw.write(pop() + "\n");
			}
			else {
				add(num);
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void add(int n) {
		heap.add(n);//끝에다가 insert
		
		//거슬러올라가기
		int i = heap.size()-1;
		while(i>1) {
			if(heap.get(i) > heap.get(i/2)) {
				int tmp = heap.get(i);
				heap.set(i, heap.get(i/2));
				heap.set(i/2, tmp);
				
				i = i/2;
			}
			else {
				break;
			}
		}
	}
	
	static int pop() {
		if(heap.size() == 1)
			return 0;
		
		int p = heap.get(1);
		
		heap.set(1, heap.get(heap.size()-1));//마지막 값과 root 자리 바꿈
		heap.remove(heap.size()-1);//마지막값 지우고
		
		//MAX-HEAPIFY
		int i = 1;
		while(i<heap.size()) {
			//자식 둘중 더 큰것의 인덱스찾기
			int biggeridx = 0;
			if(i*2+1 < heap.size()) {
				if(heap.get(i*2) > heap.get(i*2+1))
					biggeridx = i*2;
				else
					biggeridx = i*2+1;
			}
			else if(i*2 < heap.size()) {
				biggeridx = i*2;
			}
			
			//자식없으면 끝
			if(biggeridx == 0)
				break;
			
			//자식노드보다 더 큰지 확인
			if(heap.get(biggeridx) > heap.get(i)) {
				int tmp = heap.get(i);
				heap.set(i, heap.get(biggeridx));
				heap.set(biggeridx, tmp);
				
				i = biggeridx;
			}
			else {
				break;
			}
		}
		
		return p;
	}

}
