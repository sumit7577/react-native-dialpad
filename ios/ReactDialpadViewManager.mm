#import <React/RCTViewManager.h>
#import <React/RCTUIManager.h>
#import "RCTBridge.h"

@interface ReactDialpadViewManager : RCTViewManager
@end

@implementation ReactDialpadViewManager

RCT_EXPORT_MODULE(ReactDialpadView)

- (UIView *)view
{
  return [[UIView alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(color, NSString)

@end
