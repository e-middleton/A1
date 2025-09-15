# A1 DynamicArray

## Your readme should include the following information. Each student needs to submit all of this information.


Any references or resources used besides JavaDoc and course materials:
Reflection on your experience with this assignment:


I didn't work with anybody else for this assignment. I did attend office hours.
I did not use any materials other than class notes and Javadoc.


I had some trouble keeping track of everything because there were so many details in the instructions.
For example, making sure that the methods index ranges included the proper indicies, which weren't always the same, or that I included 
the correct exceptions. 
I think in the future I would make a spreadsheet when I get instructions so I can list out each of the requirements for 
each part in a very detailed checklist format so I can keep track of things better. That way I can also cross things off when I'm sure 
I've implemented them. 

One thing that I completely forgot about until I looked at the checklist was trying to reduce redundancy by having methods call each other. But I'm glad that I ended up doing that because it looks a lot less messy and it's easier to read through now that
I can just call the other methods instead of writing more loops.
I tried to think of other methods that could just call other methods, but I think the ones I didn't change would be less efficient if they called other methods so I left them as they were for now.
I should definitely read the checklist earlier, but this time, I had already written implementations of the methods before
the checklist was released so it was a bit of an odd case out.

Overall, I think my organization strategy for these assignments should be updated, and I should spend more time thinking about how 
different methods can complement each other or work together, instead of looking at them in isolation.

I realized I completely misunderstood how set(int index, T val) was supposed to work. I had previously thought I could add a value anywhere
as long as the index was within the capacity initially declared/the size of the internal array. But when I ran it through the autograder, I 
realized that I needed to add those elements before they could be set, and then I had to rewrite a large portion of the code. 

I'm really not sure that I've written my methods correctly, and I feel like I have a lot of loops in different places
that make me a little worried that I'm not being very efficient with memory.