class Solution {
   public:
       bool isValid(std::string s) {
       std::stack<char>stack;
        for(char ch : s){
            if(ch== '(')
                stack.push(')');
            else if(ch=='[')
               stack.push(']');
            else if(ch=='{')
                stack.push('}');
            else if(stack.empty() || ch !=  stack.top())
                 return false;
                else
                    stack.pop();
                }
        return stack.empty();
             
         }
};