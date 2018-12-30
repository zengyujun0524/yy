package cn.itrip.beans.vo.comment;
  //评论数量VO
public class ItripCommentCountVO {
      //有待提高
      private  long improve ;
      //值得推荐
      private  long isok;
       //带图片
      private  long havingimg;
      //所以评论
      private  long allcomment;

      public long getImprove() {
          return improve;
      }

      public void setImprove(long improve) {
          this.improve = improve;
      }

      public long getIsok() {
          return isok;
      }

      public void setIsok(long isok) {
          this.isok = isok;
      }

      public long getHavingimg() {
          return havingimg;
      }

      public void setHavingimg(long havingimg) {
          this.havingimg = havingimg;
      }

      public long getAllcomment() {
          return allcomment;
      }

      public void setAllcomment(long allcomment) {
          this.allcomment = allcomment;
      }
  }
