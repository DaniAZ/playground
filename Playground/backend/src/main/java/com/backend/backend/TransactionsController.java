package com.backend.backend;

import com.backend.backend.Statistics;
import com.backend.backend.Transactions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class TransactionsController{
    static final long ONE_MINUTE_IN_MILLIS=60000;

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/getstats")
    public Statistics getStats(){
        return transactionsService.getStats();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveTransaction(@RequestBody Transactions transaction){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));       
        Date firstDate = new Date();
        Date secondDate = new Date();
        try{
            secondDate  = sdf.parse(transaction.getTime());
        }catch (ParseException e){
        
        }
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());    
        if(diffInMillies > 0){
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
        transactionsService.saveTransaction(transaction);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteTransaction(){
        transactionsService.deleteTransaction();
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}