package com.predictionmarketing.itemrecommend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class ItemRecommend {

	public static void main(String[] args) {
		try {
			DataModel dm = new FileDataModel(new File("data/tables2.csv"));
			FileWriter dm2 = new FileWriter("data/tables3.csv", true);
			
			UserSimilarity sim = new PearsonCorrelationSimilarity (dm);
			UserNeighborhood nei = new NearestNUserNeighborhood (7,sim,dm);
			
			Recommender recommender = new GenericUserBasedRecommender (dm,nei,sim);		

			//int x=1;
			//for (LongPrimitiveIterator users = dm.getItemIDs();users.hasNext();){
			
			String tryAgain = "y";
			do {
				Scanner userId1 = new Scanner(System.in);
			    System.out.print("Input UserId : ");
				long userId = userId1.nextLong();
				//long itemId = items.nextLong();
				
				List<RecommendedItem> recommendations = recommender.recommend(userId, 3);

				
				for (RecommendedItem recommendation : recommendations) {
					System.out.println("Recommend factor " + recommendation.getItemID() + " for userId " + userId + " becuz " + recommendation.getValue() );
					

		            dm.append(userId);
		            dm.append(recommendation.getItemID());
		            dm.append(recommendation.getValue());
		           
				}
				
				Scanner tryAgain1 = new Scanner(System.in);
				System.out.println("Try again? enter \"y/n\".");
				tryAgain = tryAgain1.nextLine();

				}
				while(!tryAgain.equals("n"));
				
				//x++;
				//if(x>60) System.exit(1);
				
			//}
			


			
		} catch (IOException e) {
			System.out.println("There was an error!");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("There was a taste exception!");
			e.printStackTrace();
		}

	}

}
