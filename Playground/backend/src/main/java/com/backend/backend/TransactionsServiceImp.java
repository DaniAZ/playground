package com.backend.backend;

import com.backend.backend.Statistics;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import com.backend.backend.Transactions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Locale;

@Service
public class TransactionsServiceImp implements TransactionsService {
    static final long ONE_MINUTE_IN_MILLIS=60000;
    Hashtable<String, List<Transactions>> transactions = new Hashtable<>();

    public Statistics getStats(){
        double sum = 0;
        double max = 0;
        double min = 0;
        long count = 0;
        double avg = 0;

        final Date dateObj;
        String formatDateTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));           
        String time = sdf.format((new Date().getTime()) - ONE_MINUTE_IN_MILLIS); 
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));   
        try{
            dateObj = sdf.parse(time);
            formatDateTime = new SimpleDateFormat("MM/dd/yyyy KK:mm").format(dateObj);
        }catch (ParseException e){
    
        }
        if(transactions.containsKey(formatDateTime)){
            List<Transactions> trans = transactions.get(formatDateTime);
            min = trans.get(0).getAmount();
            max = trans.get(0).getAmount();
            for(Transactions t:trans){
                sum += t.getAmount(); 
            }
             count = trans.size();
             avg = sum/count;

            for(Transactions t:trans){
                if(max < t.getAmount()) {
                    max = t.getAmount();
                }else if( min > t.getAmount()){
                    min = t.getAmount();
                }
            }
    }
        return new Statistics(sum, avg, max, min, count);
    }

    public void saveTransaction(@RequestBody Transactions transaction){

        System.out.println("Amount:" + transaction.getAmount());
        System.out.println("Time:" +transaction.getTime());

        String formatDateTime = null;
        final Date dateObj;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));   
        try{
            dateObj = sdf.parse(transaction.getTime());
            formatDateTime = new SimpleDateFormat("MM/dd/yyyy KK:mm").format(dateObj);
        }catch (ParseException e){
    
        }
        List<Transactions> trans;
        if(transactions.containsKey(formatDateTime)){
            trans = transactions.get(formatDateTime);
            trans.add(transaction);
            transactions.put(formatDateTime, trans);
        }else{
            trans = new ArrayList<>();
            trans.add(transaction);
            transactions.put(formatDateTime, trans);
        }
        System.out.println(transactions.keySet());
    }

    public void deleteTransaction(){
        transactions.clear();
    }
}