/**
 * @author Matt
 */
/*
 * Write a program to simulate the Paging Memory Scheme. The total memory available will be of size 1000. Within memory all chunks 
 * will be of size 100. Your program should accept a text file which will have a list of jobs already created. Make sure to include 
 * input validation and error handling in your program. You never know when bad data will be inputed into your system. Each Job should
 * be of the form (id, size, execution time). All values should be of type int.

 * This scheme will require 3 tables. A Job Table, Page Map Table, and Memory Map Table. In the Job Table you can store the instances 
 * to the Page Map Table instead of an address location. The Page Map Table you can store the frame/chunk number being used instead of 
 * memory address. Finally the Memory Map Table is the combination of the Free/Busy Tables from the previous project. But it will hold 
 * the frame/chunk number instead of a partition number.

 * Your Memory Manager should continuously run until there are no more jobs available. Please make sure after each Job is entered you 
 * print out a list of all memory chunks with the Job number, Page number, and chunk number if a page is present or just the chunk 
 * number if no Page is present.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {

		ArrayList<Job> jobs = new ArrayList<>();
		Page[] mainMem = new Page[10];
		File  fileName =new File("/Users/Matt/Documents/workspace/Lab 2 Paging/src/testfile");	

		/**
		 * Reads in the text file and creates job objects
		 */
		try {
			Scanner in = new Scanner(fileName);
			System.out.println("File Found");

			//checks the contents of the file 	 
			int a = 0;
			do {

				String line = in.nextLine();
				int first = Integer.parseInt(line.substring(0, line.indexOf(',')));
				line = line.substring(line.indexOf(',')+1);
				int second = Integer.parseInt(line.substring(0, line.indexOf(',')));
				int third = Integer.parseInt(line.substring(line.indexOf(',')+1));
				if(second<=1000) {
					jobs.add(new Job(first,second,third));
					jobs.get(a).setNumOfPages();
					jobs.get(a).setPageMapTable();
					System.out.println("Accepted "+jobs.get(a).toString());
					a++;
				}
				else {
					System.out.println("Job "+first+" has been rejected. Memory too large.");
				}


			}while(true);

		} catch(IOException e){
			System.out.println("File not Found!!!");
			System.exit(1);

		} 
		catch(NoSuchElementException e) {
			System.out.println("All The Data Has Been Inputed\n");
		}
		catch(NumberFormatException e) {
			System.out.println("Bad Data!!!");
			System.exit(1);

		}
		catch(Exception e) {
			System.out.println("Unknown Error!!!");
			e.printStackTrace();
			System.exit(1);

		}	

		//creating main memory
		for(int x = 0; x <10; x++) {
			mainMem[x] = null; 
		}


		boolean c = true;
		int openSpots = 0;

		while(!jobs.isEmpty()) {
			for(int a=0;a<jobs.size();a++) {									//loop through jobs

				//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------				

				for(int x = 0; x <10; x++) {							//loop through memory locations
					if(mainMem[x] == null) {
						openSpots++;
					}	
				}

				//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------						
				
				//allocation
				if(jobs.get(a).processing == false) {
					if(openSpots>=jobs.get(a).getNumOfPages()) {
						for(int b=0;b<jobs.get(a).getNumOfPages();b++) {
							for(int x = 0; x <10; x++) {							//loop through memory locations				
								if(mainMem[x] == null && jobs.get(a).pageMapTable[b].placed == false) {
									mainMem[x] = jobs.get(a).pageMapTable[b];
									jobs.get(a).pageMapTable[b].placed = true;
									System.out.println("Placed Job: "+jobs.get(a).getID()+" > Page: "+jobs.get(a).pageMapTable[b].getPageNum()+" > in frame number "+x);
									break;
								}
							}
						}
						jobs.get(a).setStartTime(System.currentTimeMillis());
						jobs.get(a).setEndTime();
						jobs.get(a).processing = true;
						System.out.println();
						System.out.println("Current Table:");
						for(int cur = 0;cur<10;cur++) {
							if(mainMem[cur] == null) {
								System.out.println("Frame Number "+cur+" | Job:   | Page:  ");

							}
							else {
								System.out.println("Frame Number "+cur+" | Job: "+mainMem[cur].getJobID()+" | Page: "+mainMem[cur].getPageNum());
							}
						}
						System.out.println("----------------------------------------------End of Iteration");
						System.out.println();
					}
				}	

				//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

				//deallocation
				for(int g=0;g<jobs.size();g++) {
					if(jobs.get(g).getEndTime() <= System.currentTimeMillis()/* && jobs.get(g).processing == true*/) {
						for(int n=0;n<10;n++) {
							if(mainMem[n]!=null) {
								if(jobs.get(g).getID() == mainMem[n].getJobID()) {
									System.out.println("JOB: "+mainMem[n].getJobID()+" PAGE: "+mainMem[n].getPageNum() + " HAS BEEN EJECTED");
									mainMem[n].setEjected(true);
									mainMem[n] = null;

								}
							}
						}

					}	
				}	

				//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

				//job termination
				boolean amIDone = true;
				for(int h = 0;h<jobs.size();h++) {
					for(int d = 0; d <jobs.get(h).getNumOfPages(); d++) {
						if(jobs.get(h).pageMapTable[d].ejected == false) {
							amIDone = false;
							break;
						}
					}
					if(amIDone == true) {
						System.out.println("JOB: "+jobs.get(h).getID()+" HAS BEEN REMOVED");
						System.out.println();
						System.out.println("Current Table:");
						for(int cur = 0;cur<10;cur++) {
							if(mainMem[cur] == null) {
								System.out.println("Frame Number "+cur+" | Placed Job:   | Page:  ");

							}
							else {
								System.out.println("Frame Number "+cur+" | Placed Job: "+mainMem[cur].getJobID()+" | Page: "+mainMem[cur].getPageNum());
							}
						}
						System.out.println("----------------------------------------------End of Iteration");

						jobs.remove(h);
						if(jobs.isEmpty() == true) {
							System.out.println("All Jobs Complete!");
							System.exit(1);
						}
					}
				}
				//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			}
		}
	}
	
}
