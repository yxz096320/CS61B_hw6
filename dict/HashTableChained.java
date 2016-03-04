/* HashTableChained.java */

package dict;

import list.SList;
import list.List;
import list.ListNode;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/

  private List[] buckets;
  private int n;
  private int capacity;
  

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
	  while(!isPrime(sizeEstimate)){
		  n++;
	  }
	  capacity = sizeEstimate;
	  buckets = new List[sizeEstimate];
  }
  

  /** 
   *  Determine is a integer is a prime or not
   *  @param n is the input integer
   *  @return true if integer is prime
   **/

  boolean isPrime(int n){
	  if(n % 2 == 0){
		  return false;
	  }
	  for(int i = 2; i < (int)Math.sqrt(n); i += 2){
		  if(n % i == 0){
			  return false;
		  }
	  }
	  return true;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	//101 a prime number close to 100
	  capacity = 101;
	  buckets = new List[101];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return Math.abs(code % capacity);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return n;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return n == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  int k = key.hashCode();
	  k = compFunction(k);
	  Entry item = new Entry();
	  item.key = key;
	  item.value = value;
	  if(buckets[k] == null){
		  buckets[k] = new SList(); 
	  }
	  buckets[k].insertBack(item);
	  n++;
    return item;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
	  int k = key.hashCode();
	  k = compFunction(k);
	  if(buckets[k] == null){
		  return null;  
	  }
	  else{
		  try{
			  ListNode node = buckets[k].front();
			  while(((Entry)node.item()).key() != key && node.isValidNode()){
				  node = node.next();
			  }
			  if(!node.isValidNode()){
				  return null;
			  }
			  return (Entry)node.item();
		  }
		  catch(Exception e){
			  System.err.println("found an invalid node");
			  return null;
		  }
	  }
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	  int k = key.hashCode();
	  k = compFunction(k);
	  if(buckets[k] == null){
		  return null;  
	  }
	  else{
		  try{
			  ListNode node = buckets[k].front();
			  while(((Entry)node.item()).key() != key && node.isValidNode()){
				  node = node.next();
			  }
			  if(!node.isValidNode()){
				  return null;
			  }
			  node.remove();
			  n--;
			  return (Entry)node.item();
		  }
		  catch(Exception e){
			  System.err.println("found an invalid node");
			  return null;
		  }
	  }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	  buckets = new List[capacity];
	  n = 0;
  }

}
